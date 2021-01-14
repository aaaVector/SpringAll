package com.example.easy.rule.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

/**
 * @Author: kt
 * @Date: 2021/1/14 10:51
 */
@Rule
public class TwoRule {

  private int input;

  @Condition
  public boolean isTwo() {
    return input % 7 == 0;
  }

  @Action
  public void printTwo() {
    System.out.print("buzz");
  }

  public void setInput(int input) {
    this.input = input;
  }

  @Priority
  public int getPriority() {
    return 2;
  }

}
