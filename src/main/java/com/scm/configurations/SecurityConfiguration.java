package com.scm.configurations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    @Autowired
    private OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

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
        // httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/dashboard");
            formLogin.failureForwardUrl("/login?error=true");

            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            // @Override
            // public void onAuthenticationFailure(HttpServletRequest request,
            // HttpServletResponse response,
            // AuthenticationException exception) throws IOException, ServletException {
            // // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationFailure'");
            // }

            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            // @Override
            // public void onAuthenticationSuccess(HttpServletRequest request,
            // HttpServletResponse response,
            // Authentication authentication) throws IOException, ServletException {
            // // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationSuccess'");
            // }

            // })
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        // oauth configurations
        httpSecurity.oauth2Login(oAuth -> {
            oAuth.loginPage("/login");
            oAuth.successHandler(oAuthAuthenticationSuccessHandler);
        });
        return httpSecurity.build();
    }

    // configuration of authentication provider for spring security

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
