package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http.csrf(csrf->csrf.disable())
                .cors(cors->cors.disable())
                .authorizeHttpRequests(auth->auth.anyRequest().permitAll());

//        http.csrf(csrf->csrf.disable()) // Disable CSRF protection
//        .cors(cors -> cors.disable())  // Disable CORS protection
//        .authorizeHttpRequests(authz-> authz.requestMatchers("/api/v1/auth/signup","/api/v1/auth/signin","/api/v1/auth/content-manager-signup","/api/v1/auth/blog-manager-signup","/api/v1/auth/login-otp","/api/v1/auth/validate-otp").permitAll()
//        .requestMatchers("/api/v1/cars/add-car").hasRole("CONTENT_MANAGER").anyRequest().authenticated());// Allow access to specific endpoints //just permit given url and apart from that authonticate
//        System.out.println("In the security config file inside securityFilterChain method");

        // Adding custom JWT filter before the AuthorizationFilter
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);//here we are saying first do authentication using jwtFilter class before authorization using AuthorizationFileter class
        return http.build();
    }
}
