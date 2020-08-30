package com.example.webService.xmlService.controller;

import com.example.webService.xmlService.domain.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xy
 * @Date: 2020/8/30 11:47
 */
@RequestMapping("/xml")
@RestController
public class XmlController {

    /**
     * JAXB写法
     *
     * POST http://localhost/xml/query
     * Accept: application/xml;charset=utf-8
     * Cache-Control: no-cache
     * Content-Type: application/xml
     *
     * <?xml version="1.0" encoding="UTF-8" standalone="yes"?><student><code>1231223</code><name>你好</name><happys><happy>打篮球</happy><happy>345erepre</happy></happys></student>
     */
    @RequestMapping(value = "/query")
    public Student query(@RequestBody Student student) {
        System.out.println(student.toString());
        return student;
    }

}
