package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;

@Controller
public class PageController {

    @Autowired
    UserService userService;

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
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("processing registeration");

        // validate form data

        // TODO: Validate UserForm
        // save to database
        // User user = User.builder().name(userForm.getName()).profilePic(
        // "https://console.cloudinary.com/pm/c-0d9b84dca30723ba42aba23a9ecde8/media-explorer?assetId=e2ce21dc2aff7bb5441f2244704a1031")
        // .password(userForm.getPassword()).email(userForm.getEmail()).about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .build();
        User user1 = new User();
        user1.setName(userForm.getName());
        user1.setEmail(userForm.getEmail());
        user1.setAbout(userForm.getAbout());
        user1.setPassword(userForm.getPassword());
        user1.setPhoneNumber(userForm.getPhoneNumber());
        user1.setProfilePic(
                "https://console.cloudinary.com/pm/c-0d9b84dca30723ba42aba23a9ecde8/media-explorer?assetId=e2ce21dc2aff7bb5441f2244704a1031");
        // user1.setProfilePic(userForm.getProfilePic());
        // user1.setEnabled(userForm.isEnabled());
        // user1.setEmailVerified(userForm.isEmailVerified());
        // user1.setPhoneVerified(userForm.isPhoneVerified());
        // user1.setProvider(userForm.getProvider());
        // user1.setProviderId(userForm.getProviderId());
        // User saveUser = userForm.save(user1);
        userService.saveUser(user1);
        // user service
        System.out.println(userForm);

        return "redirect:/register";
    }

}
