package com.kcj.management.shop.config;

import com.kcj.management.shop.service.StaffService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers(new AntPathRequestMatcher("/img/**"))
                .requestMatchers(new AntPathRequestMatcher("/css/**"));
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new StaffService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .permitAll()
                )
                .userDetailsService(userDetailsService())
                .formLogin(loginSecurity -> {
                    loginSecurity
                            .loginPage("/login")
                            .loginProcessingUrl("/login/sign")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .successHandler((request, response, authentication) -> {
                                response.sendRedirect("/success");
                            })
                            .failureHandler((request, response, exception) -> {
                                response.sendRedirect("/fail");
                            })
                            .permitAll();
                })
                .logout(logoutSecurity -> {
                    logoutSecurity
                            .logoutUrl("/logout")
                            .logoutSuccessHandler((request, response, authentication) -> {
                                response.sendRedirect("/login");
                            })
                            .permitAll();
                });

        return http.build();
    }
}
