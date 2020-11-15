package com.posjava.redesocial.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldResource {

    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }

    @PostMapping("/hello")
    public String hellor(String hello){
        return hello;
    }
}
