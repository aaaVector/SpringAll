package com.example.webService.axis2.webService;

import org.springframework.stereotype.Component;

/**
 * @Author: xy
 * @Date: 2020/9/4 23:21
 */
@Component
public class NameService {

    public String getName(String name) {
        System.out.println("your name is " + name);
        return "hello " + name;
    }

}
