package com.example.easy.rule.springeasyrule.bean;

import org.springframework.stereotype.Component;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:36
 */
@Component
public class MyService {

  public void setInfo(User user){
    user.setName("rongfengliang");
  }

}
