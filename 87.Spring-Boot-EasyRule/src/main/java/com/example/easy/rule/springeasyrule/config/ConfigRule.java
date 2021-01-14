package com.example.easy.rule.springeasyrule.config;

import com.example.easy.rule.Application;
import com.example.easy.rule.springeasyrule.bean.UserService;
import java.io.FileReader;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.JsonRuleDefinitionReader;
import org.mvel2.ParserContext;
import org.springframework.stereotype.Component;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:39
 */
@Component
public class ConfigRule {

  public Rules fetchConfigRules() throws Exception {
    MVELRuleFactory ruleFactory = new MVELRuleFactory(new JsonRuleDefinitionReader());
    ParserContext context =new ParserContext();
    context.addImport("UserService", UserService.class);
    Rules jsonRules = ruleFactory.createRules(new FileReader(
        Application.class.getClassLoader().getResource("rules-json.json").getFile()),context);
    return jsonRules;
  }

}
