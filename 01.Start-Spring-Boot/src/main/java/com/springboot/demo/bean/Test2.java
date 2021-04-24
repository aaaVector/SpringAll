package com.springboot.demo.bean;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: kt
 * @Date: 2021/1/14 18:02
 */
@Component
public class Test2 {

  public void start() throws InterruptedException {
    final Thread t1 = new Thread(() -> {
      System.out.println("aaa");
//      try {
//        Thread.sleep(3000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
      while(true) {
        LockSupport.park(this);
        //判断是否中断 要用interrupted方法清除标记，否则park直接返回cpu空转
        System.out.println(Thread.currentThread().isInterrupted());

      }
    }, "t1");
    final Thread t2 = new Thread(() -> {
      try {
        t1.interrupt();
        Thread.sleep(3000);
        System.out.println("开始中断");
        //LockSupport.unpark();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "t2");
    t1.start();
    Thread.sleep(1000);
    t2.start();
  }

  public static void main(String[] args) throws InterruptedException {
    new Test2().start();
  }

}
