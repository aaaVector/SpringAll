package com.canal.client.server.configuration;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import com.canal.client.server.properties.CanalServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: xy
 * @Date: 2020/12/13 19:13
 */
public class CanalServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CanalServer.class);

    private final CanalServerProperties canalServerProperties;
    private final EntryParser entryParser;

    /**
     * 启动次数
     */
    private LongAdder runners = new LongAdder();

    public int getRunners() {
        return runners.intValue();
    }

    public CanalServer(CanalServerProperties canalServerProperties, EntryParser entryParser) {
        this.canalServerProperties = canalServerProperties;
        this.entryParser = entryParser;
    }

    protected void run() {
        final CanalConnector connector = getClient();
        runners.increment();
        try {
            connector.connect();
            connector.rollback();
            Message message;
            while (true) {
                connector.subscribe();
                // 获取指定数量的数据
                message = connector.getWithoutAck(Integer.parseInt(canalServerProperties.getBatchSize()));
                if (message.getId() == -1 || message.getEntries().size() == 0) {
                    try {
                        // 等待时间
                        Thread.sleep(Integer.parseInt(canalServerProperties.getSleep()));
                    } catch (InterruptedException e) {
//                        log.error("线程睡眠异常{}", e);
                        System.out.println("线程睡眠异常," + e);
                    }
                } else {
                    entryParser.parseEntryList(message.getEntries());
                }
                // 提交确认
                connector.ack(message.getId());
            }
        } finally {
            runners.decrement();
            LOGGER.error("connect error!");
            connector.disconnect();
        }
    }

    protected CanalConnector getClient() {
        CanalConnector canalConnector;
        if(canalServerProperties.isCluster()) {
            if (!StringUtils.hasText(canalServerProperties.getZkServer())) {
                canalConnector= CanalConnectors.newClusterConnector(canalServerProperties.getZkServer(),
                        canalServerProperties.getInstance(),
                        canalServerProperties.getUserName(),
                        canalServerProperties.getPassword());
            }else {
                List<InetSocketAddress> inetSocketAddresses;
                if(canalServerProperties.getHost().contains(",")) {
                    String[] ips = canalServerProperties.getHost().split(",");
                    inetSocketAddresses = new ArrayList<>(ips.length);
                    for (String s : ips) {
                        String[] ipAndPort = s.split(",");
                        inetSocketAddresses.add(new InetSocketAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1])));
                    }
                }else {
                    String[] ips = canalServerProperties.getHost().split(":");
                    inetSocketAddresses = Collections.singletonList(new InetSocketAddress(ips[0], Integer.parseInt(ips[1])));
                }
                canalConnector = CanalConnectors.newClusterConnector(inetSocketAddresses,
                        canalServerProperties.getInstance(),
                        canalServerProperties.getUserName(),
                        canalServerProperties.getPassword());
            }
        }else {
            String[] ips = canalServerProperties.getHost().split(":");
            canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress(ips[0], Integer.parseInt(ips[1])),
                    canalServerProperties.getInstance(),
                    canalServerProperties.getUserName(),
                    canalServerProperties.getPassword()
            );
        }
        return canalConnector;
    }

}
