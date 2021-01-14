package com.example.easy.rule.springeasyrule.config;

import com.example.easy.rule.springeasyrule.listener.MyRuleEngineListener;
import com.example.easy.rule.springeasyrule.listener.MyRulesListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:42
 */
@Component
public class ProtoRulesEngineFactoryBean implements FactoryBean<RulesEngine> {

  Logger log = LoggerFactory.getLogger(MyRulesListener.class);
  public RulesEngine init() {
    log.info("create rule Engine");
    DefaultRulesEngine rulesEngine = new DefaultRulesEngine();
    rulesEngine.registerRuleListener(new MyRulesListener());
    rulesEngine.registerRulesEngineListener(new MyRuleEngineListener());
    return rulesEngine;
  }

  @Override
  public RulesEngine getObject() throws Exception {
    return this.init();
  }

  @Override
  public Class<?> getObjectType() {
    return RulesEngine.class;
  }

  @Override
  public boolean isSingleton() {
    return false;
  }
}
