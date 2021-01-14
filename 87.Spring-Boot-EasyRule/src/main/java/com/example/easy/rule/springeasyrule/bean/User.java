package com.example.easy.rule.springeasyrule.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:24
 */
public class User {

  private String name;
  private Integer age;
  @JsonIgnore
  private transient String uniqueId;
  private Map<Object, Object> userinfo;

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", uniqueId='" + uniqueId + '\'' +
        ", userinfo=" + userinfo +
        '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  public Map<Object, Object> getUserinfo() {
    return userinfo;
  }

  public void setUserinfo(Map<Object, Object> userinfo) {
    this.userinfo = userinfo;
  }
}
