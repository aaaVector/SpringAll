package com.example.webService.xmlService.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author: xy
 * @Date: 2020/8/30 10:42
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ProductGroup")
@XmlType(propOrder = {
    "code",
    "version",
})
public class StudentGroup implements Serializable {

    private String code;
    private String version;

}
