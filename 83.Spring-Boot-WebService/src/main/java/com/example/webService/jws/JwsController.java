package com.example.webService.jws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xy
 * @Date: 2020/8/30 21:17
 */
@RequestMapping("/jws")
@RestController
public class JwsController {

    @RequestMapping("/hi")
    public String test() {
        return "hello, test!";
    }
}
