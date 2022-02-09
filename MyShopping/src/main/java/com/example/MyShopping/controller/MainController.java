package com.example.MyShopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("")
    public String defaultPage(){
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "user/login";
    }


}
