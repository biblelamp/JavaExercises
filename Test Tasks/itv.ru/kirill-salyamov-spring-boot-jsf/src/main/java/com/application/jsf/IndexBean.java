package com.application.jsf;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * User: kirill.salyamov
 * Time: 2016-05-06 18:13
 */
@Controller
@Scope("request")
public class IndexBean implements Serializable {

    @PostConstruct
    public void init() {
        System.out.println("hello");
    }

    public String getTitle() {
        return "HelloWorldPage";
    }

    public String getText() {
        return "HelloWorld";
    }
}