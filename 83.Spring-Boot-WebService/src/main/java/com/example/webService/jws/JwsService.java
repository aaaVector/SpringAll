package com.example.webService.jws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Author: xy
 * @Date: 2020/8/30 21:22
 */

@WebService
public interface JwsService {

    @WebMethod
    String hiJwsService(@WebParam(name = "hi") String s);

}
