package com.example.easy.rule.springeasyrule.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:57
 *
 * 获取rule 结果的帮助rule
 * rule的目的比较简单，因为很多时候我们需要获取结果数据，所以规则运行的时候会注册一个帮助rule，方便获取结果数据
 */
@Rule(name = "100", description = "my rule description", priority = 100)
public class MyRule<T> {

  private boolean executed;
  private T result;

  @Condition
  public boolean when(@Fact("user") T fact) {
    //my rule conditions
    return true;
  }

  @Action(order = 1)
  public void then(@Fact("user") T facts) throws Exception {
    try {
      System.out.println("my rule has been executed");
      result = (T)facts; // assign your result here
      executed = true;
    } catch (Exception e) {
      // executed flag will remain false if an exception occurs
      throw e;
    }
  }

  @Action(order = 2)
  public void finaly() throws Exception {
    try {
      System.out.println("all is ok ");
    } catch (Exception e) {
      // executed flag will remain false if an exception occurs
      throw e;
    }
  }
  public boolean isExecuted() {
    return executed;
  }
  public T getResult() {
    return result;
  }

}
