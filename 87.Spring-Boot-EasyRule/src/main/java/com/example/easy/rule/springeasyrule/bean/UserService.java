package com.example.easy.rule.springeasyrule.bean;

import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: kt
 * @Date: 2021/1/14 14:32
 */
public class UserService {

  static Logger log = LoggerFactory.getLogger(UserService.class);

  public static void doAction1(User user){
    log.info("------------do action1------------");
    log.info(user.toString());
  }

  public static void doAction2(User user){
    log.info("------------do action2------------");
    user.setName("sssssssssssssssssss");
    log.info(user.toString());
  }
  public static void doAction3(Map user){
    log.info("------------do action3------------");
    log.info(user.toString());
  }

  public static void appendName(User user){
    /**
     * 通过静态全局ApplicationContext 获取bean引用
     */
    MyService myService =SpringBeanUtil.getBean(MyService.class);
    myService.setInfo(user);
  }
  public static void doAction4(Object user){
    /**
     * 基于mvel 修改数据，为了复用doAction4,做了数据兼容处理
     */
    if (user instanceof Map ){
      log.info("------------do action4------------");
      ((Map)user).put("name2","rule do action4");
    }
    if (user instanceof User) {
      ((User)user).setName("dalong demo appapapappapa");
    }
    log.info(user.toString());
  }

}
