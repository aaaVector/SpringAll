package com.canal.client.server.configuration;

import com.canal.client.server.properties.CanalServerProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author: xy
 * @Date: 2020/12/13 19:09
 */
@SpringBootConfiguration
@EnableConfigurationProperties(CanalServerProperties.class)
public class CanalClientAutoConfiguration {

    @Bean
    public EntryParser entryParser() {
        return new EntryParser();
    }

    @Bean
    public CanalServer canalServer(CanalServerProperties canalServerProperties) {
        return new CanalServer(canalServerProperties, entryParser());
    }

}
