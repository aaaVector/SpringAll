package com.example.easy.rule;

import com.example.easy.rule.rules.NoOneTwoRule;
import com.example.easy.rule.rules.OneRule;
import com.example.easy.rule.rules.ThreeRule;
import com.example.easy.rule.rules.TwoRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    //SpringApplication.run(Application.class, args);
    runActionWithEasyRule();
  }

  private static void runActionWithEasyRule() {
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
