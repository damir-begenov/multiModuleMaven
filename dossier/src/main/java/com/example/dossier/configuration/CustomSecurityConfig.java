//package com.example.dossier.configuration;
//
//import com.example.security.security.jwt.AuthTokenFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//
//@Configuration
//@Order(1)
//@ComponentScan(basePackages = {"com.example.security.security.jwt", "com.example.security.security.services","com.example.security.security.repository"})
//public class CustomSecurityConfig {
//
//    @Bean(name = "customAuthenticationJwtTokenFilter")
//    public AuthTokenFilter customAuthenticationJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }
//}
