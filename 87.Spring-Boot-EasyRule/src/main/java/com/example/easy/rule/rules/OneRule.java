package com.example.easy.rule.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

/**
 * @Author: kt
 * @Date: 2021/1/14 10:48
 */

@Rule
public class OneRule {

  private int input;

  @Condition
  public boolean isOne() {
    return input % 5 == 0;
  }

  @Action
  public void printOne() {
      System.out.println("OneRule");
    }

    public void setInput(int input) {
      this.input = input;
  }

  @Priority
  public int getPriority() {
    return 1;
  }

}
