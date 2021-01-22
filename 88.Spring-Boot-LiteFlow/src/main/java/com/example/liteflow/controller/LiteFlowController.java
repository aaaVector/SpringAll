package com.example.liteflow.controller;

import com.yomahub.liteflow.core.FlowExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kt
 * @Date: 2021/1/21 14:34
 */
@RestController
@RequestMapping("/flow")
public class LiteFlowController {

  @Autowired
  private FlowExecutor flowExecutor;

  @RequestMapping("/start")
  public String startTest() throws Exception {
    flowExecutor.execute("c1", "启动参数");
    return "";
  }

}
