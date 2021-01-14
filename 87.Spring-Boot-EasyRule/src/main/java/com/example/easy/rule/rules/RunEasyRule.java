package com.example.easy.rule.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:13
 */
public class RunEasyRule {

  public static void main(String[] args) {
    new RunEasyRule().runActionWithEasyRule();
  }

  private void runActionWithEasyRule() {
    // create a rules engine  skipOnFirstAppliedRule 如果第一个规则满足条件，后面的规则将不再执行
    RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
    DefaultRulesEngine defaultRulesEngine = new DefaultRulesEngine(parameters);

    // create rules
    Rules rules = new Rules();
    rules.register(new OneRule());
    rules.register(new TwoRule());
    rules.register(new ThreeRule(new OneRule(), new TwoRule()));
    rules.register(new NoOneTwoRule());

    // fire rules
    Facts facts = new Facts();
    for (int i = 1; i <= 100; i++) {
      facts.put("number", i);
      defaultRulesEngine.fire(rules, facts);
    }

  }

}
