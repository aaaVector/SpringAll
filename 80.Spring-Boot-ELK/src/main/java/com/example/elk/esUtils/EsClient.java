package com.example.elk.esUtils;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: vector
 * @Date: 2020/8/16 13:39
 */
@Service
public class EsClient {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void createIndex() {
        CreateIndexRequest request = new CreateIndexRequest("mess");

    }

}
