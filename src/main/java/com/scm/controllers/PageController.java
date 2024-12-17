package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.forms.UserForm;

@Controller
public class PageController {

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page Handler");
        model.addAttribute("name", "Perla Nagarjuna Reddy");
        model.addAttribute("profession", "Software Engineer");
        model.addAttribute("githubRepo", "https://github.com/perla-nagarjuna-reddy");
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

    // contact page

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
    public String register(Model model) {
        UserForm userForm = new UserForm();

        userForm.setName("Perla Nagarjuna Reddy");
        // default also we can send
        model.addAttribute("userForm", userForm);
        return new String("register");
    }

    // processing register
    @RequestMapping(value = "/do-register", method = { RequestMethod.GET, RequestMethod.POST })
    public String processRegister() {
        System.out.println("processing registeration");
        return "redirect:/register";
    }

}
