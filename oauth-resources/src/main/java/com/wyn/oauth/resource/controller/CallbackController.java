package com.wyn.oauth.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("callback")
public class CallbackController {

    @GetMapping("weibo")
    @PreAuthorize("hasAnyAuthority('callback/weibo')")
    public String callbackWeibo(){
        return "Hello World wei bo";
    }
}