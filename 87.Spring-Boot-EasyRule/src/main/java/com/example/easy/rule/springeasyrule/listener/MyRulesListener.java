package com.example.easy.rule.springeasyrule.listener;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;
import org.springframework.stereotype.Component;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:48
 */
@Component
public class MyRulesListener implements RuleListener {

  @Override
  public boolean beforeEvaluate(Rule rule, Facts facts) {
    return false;
  }

  @Override
  public void afterEvaluate(Rule rule, Facts facts, boolean b) {

  }

  @Override
  public void beforeExecute(Rule rule, Facts facts) {

  }

  @Override
  public void onSuccess(Rule rule, Facts facts) {

  }

  @Override
  public void onFailure(Rule rule, Facts facts, Exception e) {

  }
}
