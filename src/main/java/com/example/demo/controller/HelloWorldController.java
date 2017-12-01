package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.properties.NeoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*方法都以json格式输出*/
@RestController
public class HelloWorldController {
    @Autowired
    NeoProperties neoProperties;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/hello5")
    public String index1() {
        return "Hello World1";
    }

    @RequestMapping("/getUser")
    public void getUser() {
        return;
    }

    @RequestMapping("/getProperties")
    public NeoProperties getProperties() {
        return neoProperties;
    }
}