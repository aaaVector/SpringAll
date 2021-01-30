package com.springboot.demo.controller;

import com.springboot.demo.config.ApiMapping;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: xy
 * @Date: 2021/1/30 16:19
 */
@RestController
public class TestMultiAnnotationController {

    /**
     * 如果有自定义注解，再加上PostMapping则所有接口走这里
     */
    @ApiMapping(params = "method=111")
    public String test1(@RequestBody KKK kkk) {
        return "test1111";
    }

    /**
     * 多个注解的情况下 最终会以最下面的注解为准
     */
    @ApiMapping(params = "method=222")
    public String test2(@RequestBody KKK kkk) {
        return "test2222";
    }

    @PostMapping
    @ApiMapping(params = "method=333")
    public String test3(@RequestBody KKK kkk) {
        return "test333";
    }

    public static void main(String[] args) throws NoSuchMethodException {
        final Method test2 = TestMultiAnnotationController.class.getMethod("test2", KKK.class);
        final Annotation[] annotations = test2.getAnnotations();
        final PostMapping annotation = test2.getAnnotation(PostMapping.class);
        final Annotation[] annotations1 = PostMapping.class.getAnnotations();
        for (int i = 0; i < annotations1.length; i++) {
            System.out.println(annotations1[i]);
        }

    }

    public static class KKK {
        private String name;
        private Integer age;
        private Boolean health;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Boolean getHealth() {
            return health;
        }

        public void setHealth(Boolean health) {
            this.health = health;
        }
    }

}
