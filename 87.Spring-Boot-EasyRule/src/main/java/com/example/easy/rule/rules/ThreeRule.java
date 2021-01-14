package com.example.easy.rule.rules;

import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.support.CompositeRule;

/**
 * @Author: kt
 * @Date: 2021/1/14 10:52
 * ThreeRule，这是一个组合规则，它由OneRule和TwoRule组成
 */
@Rule
public class ThreeRule extends CompositeRule {

  public ThreeRule(Object... rules) {
    for (Object rule : rules) {
      addRule(rule);
    }
  }

  @Override
  public boolean evaluate(Facts facts) {
    return false;
  }

  @Override
  public void execute(Facts facts) throws Exception {

  }

  @Override
  public int getPriority() {
    return 0;
  }
}
