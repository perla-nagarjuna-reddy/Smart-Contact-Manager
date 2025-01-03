package com.scm.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    // user dashboard
    @RequestMapping(value = "/dashboard", method = { RequestMethod.GET, RequestMethod.POST })
    public String userdashboard() {
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile", method = { RequestMethod.GET, RequestMethod.POST })
    public String userProfile(Model model, Authentication authentication) {
        // String userNam
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        // String name = principal.getName();
        // System.out.println(name);
        model.addAttribute("loggedInUser", user);
        logger.info("User logged in : {}", username);
        return "user/profile";
    }
    // user add contacts

    // user view contacts

    // user edit contacts

    // user delete contacts

    // user search contacts

}
