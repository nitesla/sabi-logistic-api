//package com.sabilogistics.api.helper;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableAsync
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//        .antMatchers(new String[]{"/", "/wsdl", "/transactions/request"}).permitAll();
//    http.csrf().disable();
//  }
//}
