package com.canal.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 使用binder进行属性绑定
 * Binder binder = Binder.get(context.getEnvironment());
 * CanalServer canalServer = binder.bind("canal.server", Bindable.of(CanalServer.class)).get();
 */
@SpringBootApplication
public class CanalClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalClientApplication.class, args);
    }

}
