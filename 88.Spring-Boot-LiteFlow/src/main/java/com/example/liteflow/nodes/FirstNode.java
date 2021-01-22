package com.example.liteflow.nodes;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @Author: kt
 * @Date: 2021/1/19 16:30
 */
@Component
public class FirstNode extends NodeComponent {

  @Override
  public void process() throws Exception {
    final Object requestData = this.getSlot().getRequestData();

  }
}
