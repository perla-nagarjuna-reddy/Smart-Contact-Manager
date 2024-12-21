package com.scm.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // user dashboard
    @RequestMapping(value = "/dashboard", method = { RequestMethod.GET, RequestMethod.POST })
    public String userdashboard() {
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile", method = { RequestMethod.GET, RequestMethod.POST })
    public String userProfile(Authentication authentication) {

        String emailOfLoggedInUser = Helper.getEmailOfLoggedInUser(authentication);
        // String name = principal.getName();
        // System.out.println(name);
        logger.info("User logged in : {}", emailOfLoggedInUser);
        return "user/profile";
    }
    // user add contacts

    // user view contacts

    // user edit contacts

    // user delete contacts

    // user search contacts

}
