package com.example.elk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: vector
 * @Date: 2020/8/15 23:10
 */
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    public DemoController() {
        log.info("测试日志输出，hello world!");
    }

    @RequestMapping("/sendLog")
    public void sendLog() {
        log.info("测试elk的info日志");
        log.debug("测试elk的info日志");
        log.warn("测试elk的warn日志");
        log.error("测试elk的error日志");
        log.trace("测试elk的trace日志");
    }


}
