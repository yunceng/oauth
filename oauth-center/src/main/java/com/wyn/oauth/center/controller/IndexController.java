package com.wyn.oauth.center.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = {"/", ""})
    private String index(){
        return "Hello World";
    }

}