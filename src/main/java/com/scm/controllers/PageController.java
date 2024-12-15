package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    
    @GetMapping("/home")
    public String home(Model model){
        System.out.println("Home Page Handler");
        model.addAttribute("name", "Perla Nagarjuna Reddy");
        model.addAttribute("profession","Software Engineer");
        model.addAttribute("githubRepo","https://github.com/perla-nagarjuna-reddy");
        return "home";
    }

    @GetMapping("/about")
    public String AboutPage() {
        System.out.println("About Page Loading");
        return "about";
    }

    @GetMapping("/services")
    public String servicePage() {
        System.out.println("Service Page Loading");
        return "services";
    }

    //contact page

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    
    // register page

    @GetMapping("/register")
    public String register() {
        return new String("register");
    }
    
    

    
}
