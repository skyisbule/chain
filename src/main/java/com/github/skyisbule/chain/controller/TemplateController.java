package com.github.skyisbule.chain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/admin_list")
    public String adminShowAll(){
        return "admin_list";
    }

    @RequestMapping("/admin_check")
    public String adminCheck(){
        return "admin_check";
    }

    @RequestMapping("/school_insert")
    public String schoolInsert(){
        return "school_insert";
    }

    @RequestMapping("/school_list")
    public String schoolList(){
        return "school_list";
    }

}
