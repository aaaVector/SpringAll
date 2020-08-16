package com.example.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: vector
 * @Date: 2020/8/15 22:43
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringElkApplication {

    public static void main(String[] args){
        SpringApplication.run(SpringElkApplication.class, args);
    }

}
