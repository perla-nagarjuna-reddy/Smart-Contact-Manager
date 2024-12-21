package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {

        AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal();

        // we logged in using email and passwor ...how we get email

        if (authentication instanceof OAuth2AuthenticationToken) {
            var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from google client");
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("Getting email from github client");
            } else {
                System.out.println("Getting email from github client");
            }
        } else {
            System.out.println("Getting email from unknowmn client");
            return authentication.getName();
        }

        // sign with github

        // sign with google
        return "";
    }
}
