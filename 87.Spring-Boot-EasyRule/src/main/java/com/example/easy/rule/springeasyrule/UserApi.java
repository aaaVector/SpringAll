package com.example.easy.rule.springeasyrule;

import com.example.easy.rule.springeasyrule.bean.SpringBeanUtil;
import com.example.easy.rule.springeasyrule.bean.User;
import com.example.easy.rule.springeasyrule.config.ConfigRule;
import com.example.easy.rule.springeasyrule.rules.MyRule;
import java.util.UUID;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kt
 * @Date: 2021/1/14 15:02
 */
@RestController
public class UserApi {

  private final RulesEngine myEngine;

  private final ConfigRule configRule;

  public UserApi(RulesEngine myEngine, ConfigRule configRule) {
    this.myEngine = myEngine;
    this.configRule = configRule;
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Object info(@RequestBody User user) throws Exception {
    Rules rules = configRule.fetchConfigRules();
    Facts facts = new Facts();
    // 生成一个唯一id，方便基于数据id规则流程查询
    user.setUniqueId(UUID.randomUUID().toString());
    MyRule<User> rule = new MyRule<User>();
    rules.register(rule);
    facts.put("user", user);
    // 默认模式
    // myEngine.fire(rules,facts);
    // 应该使用原型模式
    SpringBeanUtil.getBean("myEngine",RulesEngine.class).fire(rules,facts);
    if(rule.isExecuted()){
      User userResult= rule.getResult();
      System.out.println("result from final ruls"+userResult.toString());
      return userResult;
    }
    else {
      return null;
    }

  }

}
