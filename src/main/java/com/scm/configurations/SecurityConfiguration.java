package com.scm.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.scm.services.impl.SecurityCustomUserDetailService;

@Component
public class SecurityConfiguration {
    // create user and login using code with in memory Service

    // @Bean
    // public UserDetailsService userDetailsService() {

    // // create user

    // UserDetails user =
    // User.withDefaultPasswordEncoder().username("admin").password("nagarjuna")
    // .roles("ADMIN", "USER").build();

    // UserDetails user1 =
    // User.withUsername("admin1").password("naga").roles("ADMIN", "USER").build();

    // var inmemoryUserDetailsManager = new InMemoryUserDetailsManager(user, user1);

    // return inmemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration for urls,,,which url should be public and which url sholuld be
        // private
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // form default login
        // whatever we are changin here, that will come here...saare form related
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
