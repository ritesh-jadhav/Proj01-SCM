package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("title","this is the technical thing");
        model.addAttribute("github","https://github.com/ritesh-jadhav");
        System.out.println("home request");
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    @RequestMapping("/services")
    public String services(){
        return "services";
    }
}
