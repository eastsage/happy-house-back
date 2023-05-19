package com.happyhome.configuration;

import com.happyhome.filter.CustomAuthenticationFilter;
import com.happyhome.handler.CustomAuthenticationFailureHandler;
import com.happyhome.handler.CustomAuthenticationSuccessHandler;
import com.happyhome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManager authenticationManager = authenticationManagerBean(
                httpSecurity.getSharedObject(AuthenticationConfiguration.class));

        CustomAuthenticationFilter customAuthenticationFilter = customAuthenticationFilter(authenticationManager);
        httpSecurity.addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity
                .formLogin().disable()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/user/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(AuthenticationManager authenticationManager)
            throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(
                new AntPathRequestMatcher("/login", HttpMethod.POST.name())
        );
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());

        return filter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }


}