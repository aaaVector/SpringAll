package com.canal.client.server.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: xy
 * @Date: 2020/12/13 21:12
 */
@Component
@EnableScheduling
public class RunnersSchedule {

    @Autowired
    private CanalServer canalServer;
    private Executor executor = Executors.newFixedThreadPool(2);
    private static final Logger LOGGER = LoggerFactory.getLogger(EntryParser.class);
    private final int times = 1;

    /**
     * 健康检查
     */
    @Scheduled(cron = "0/100 * * * * ? ")
    public void healthCheck(){
        LOGGER.info("**********************************定时任务启动**********************************");
        int runners = canalServer.getRunners();
        LOGGER.info("**********************************CannalRunner启动实例数:{}**********************",runners);
        if (runners < times){
            executor.execute(() -> canalServer.run());
        }
        LOGGER.info("**********************************定时任务结束**********************************");
    }

}
