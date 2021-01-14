package com.example.easy.rule.springeasyrule.config;

import com.example.easy.rule.springeasyrule.listener.MyRuleEngineListener;
import com.example.easy.rule.springeasyrule.listener.MyRulesListener;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:50
 * 以上是一个简单的集成学习，实际上如果我么的业务都是基于此模式开发的，那么我们可以基于配置的规则以及记录的事件，提供一个
 * 便捷的业务状态追踪系统（事件，规则，数据。。。），类似的工作流模式也是一种不错的实践
 */
@Configuration
public class EasyRuleConfiguration {

  Logger log = LoggerFactory.getLogger(MyRulesListener.class);

  /**
   * 为了方便处理以及线程安全，我们使用原型bean
   * @param myRulesListener
   * @param myRuleEngineListener
   * @return
   */
  @Bean(name = "myEngine")
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public RulesEngine myEngine(MyRulesListener myRulesListener, MyRuleEngineListener myRuleEngineListener) {
    log.info("create rule Engine");
    DefaultRulesEngine rulesEngine = new DefaultRulesEngine();
    rulesEngine.registerRuleListener(myRulesListener);
    rulesEngine.registerRulesEngineListener(myRuleEngineListener);
    return rulesEngine;
  }

  @Bean("myEngine2")
  ProtoRulesEngineFactoryBean rulesEngineFactoryBean() {
    return new ProtoRulesEngineFactoryBean();
  }

}
