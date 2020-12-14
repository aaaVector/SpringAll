package com.canal.client.server.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xy
 * @Date: 2020/12/13 19:42
 */
public class EntryParser {

    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    private static final Logger LOGGER = LoggerFactory.getLogger(EntryParser.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * 批量解析事件
     * @param list 条目集合
     */
    public void parseEntryList(List<CanalEntry.Entry> list) {
        list.forEach(this::parseEntry);
    }

    /**
     * 解析单个事件
     * @param entry 条目
     */
    public void parseEntry(CanalEntry.Entry entry) {
        if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
            return;
        }

        CanalEntry.RowChange rowChange;
        try {
            rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
        } catch (Exception e) {
            throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(), e);
        }

        //单条 binlog sql
        CanalEntry.EventType eventType = rowChange.getEventType();
        LOGGER.info("*****************开始解析->binlog[{}:{}],name[{},{}],eventType:{}*****************",
                entry.getHeader().getLogfileName(),
                entry.getHeader().getLogfileOffset(),
                entry.getHeader().getSchemaName(),
                entry.getHeader().getTableName(),
                eventType
        );
        rowChange.getRowDatasList().forEach(rowData -> parseRowData(entry.getHeader(),eventType, rowData));
        LOGGER.info("*****************解析结束******",
                entry.getHeader().getLogfileName(),
                entry.getHeader().getLogfileOffset(),
                entry.getHeader().getSchemaName(),
                entry.getHeader().getTableName(),
                eventType
        );
    }

    /**
     * 解析单行SQL数据
     * @param header 头
     * @param eventType 事件类型
     * @param rowData 行数据
     */
    public void parseRowData(CanalEntry.Header header, CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        String tableName = header.getSchemaName() + "." + header.getTableName();
        if (eventType == CanalEntry.EventType.DELETE) {
            saveRowData(tableName + ".DELETE",rowData.getBeforeColumnsList());
        } else if (eventType == CanalEntry.EventType.INSERT) {
            saveRowData(tableName + ".INSERT",rowData.getAfterColumnsList());
        } else if (eventType == CanalEntry.EventType.UPDATE){
            saveRowData(tableName + ".UPDATE",rowData.getAfterColumnsList());
        }
    }

    /**
     * 保存行数据
     * @param tableEventType 表数据事件类型
     * @param columns 表字段数据
     */
    public void saveRowData(String tableEventType, List<CanalEntry.Column> columns) {
        Map<String, String> map = new HashMap<>(15);
        columns.forEach(column -> map.put(lineToHump(column.getName()),column.getValue()));
        LOGGER.info("json解析:{}", JSON.toJSON(map));
        final QueueInformation queueInfo = amqpAdmin.getQueueInfo(tableEventType.toUpperCase());
        if(queueInfo == null) {
            amqpAdmin.declareQueue(new Queue(tableEventType.toUpperCase(), true));
        }
        rabbitTemplate.convertAndSend(tableEventType.toUpperCase(),JSON.toJSONString(map));
    }

    /**
     * 下划线转驼峰
     * @return string
     */
    public String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
