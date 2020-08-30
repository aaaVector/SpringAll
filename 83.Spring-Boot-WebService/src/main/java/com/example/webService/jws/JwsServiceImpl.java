package com.example.webService.jws;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @Author: xy
 * @Date: 2020/8/30 21:23
 */
@Service
@WebService
public class JwsServiceImpl implements JwsService{

    @Override
    public String hiJwsService(String s) {
        String msg = "获取内容："+s;
        System.out.println(msg);
        return msg;
    }

}
