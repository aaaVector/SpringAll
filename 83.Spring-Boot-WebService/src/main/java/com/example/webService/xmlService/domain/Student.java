package com.example.webService.xmlService.domain;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: xy
 * @Date: 2020/8/30 10:25
 */
@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "student")
@XmlType(propOrder = {
    "code", "name", "age", "sex", "happy"
})
public class Student implements Serializable {

    private String code;
    private String name;
    private Integer age;
    private String sex;

    @XmlElementWrapper(name = "happys")
    @XmlElement(name = "happy")
    private List<String> happy;

}
