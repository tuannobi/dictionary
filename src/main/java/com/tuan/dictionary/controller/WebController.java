package com.tuan.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/web")
@Controller
public class WebController {
    @GetMapping("/admin/login")
    public String login(){
        return "admin/login";
    }
    
    @GetMapping("/register")
    public String register() {
    	return "register";
    }
}
