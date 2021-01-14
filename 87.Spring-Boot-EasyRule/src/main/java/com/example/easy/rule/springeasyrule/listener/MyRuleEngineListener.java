package com.example.easy.rule.springeasyrule.listener;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineListener;
import org.springframework.stereotype.Component;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:48
 */
@Component
public class MyRuleEngineListener implements RulesEngineListener {

  @Override
  public void beforeEvaluate(Rules rules, Facts facts) {

  }

  @Override
  public void afterExecute(Rules rules, Facts facts) {

  }
}
