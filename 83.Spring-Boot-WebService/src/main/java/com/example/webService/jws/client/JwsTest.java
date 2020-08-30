package com.example.webService.jws.client;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @Author: xy
 * @Date: 2020/8/30 21:36
 */
public class JwsTest {

    public static void main(String[] args) {
        JwsServiceImpl jwsService = new JwsServiceImplService().getJwsServiceImplPort();
        String s = jwsService.hiJwsService("你好啊");
        System.out.println(s);
    }

}
