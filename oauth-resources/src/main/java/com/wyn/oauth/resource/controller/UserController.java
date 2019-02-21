package com.wyn.oauth.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "getUser")
    @PreAuthorize("hasAnyAuthority('user/view')")
    public String getUser(){
        return "order";
    }

}