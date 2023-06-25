package com.rollingstone.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/mainlayout.html", "/actuator/**", "/actuator-docs", "fragments/**", "/spring-security", "/header", "/security", "/oidc", "/oauth2", "/jwt","/keycloak", "/asymmetric-encryption",
                                "/asymmetric-encryption", "/spring-di","/aop", "/spring-event","/spring-data-jpa",
                                "/interface-driven-programming","/springsec-architecture", "/actuator", "/prometheus", "/elastic","/keycloak-multi-idp", "/owasp-top-ten", "/spring-boot", "/spring-boot", "/error", "/resources/**", "/css/**","/js/**", "../img/**", "/assets/**", "/webjars/**").permitAll()
                        .anyRequest().authenticated())
                //.oauth2Login(withDefaults())
                .oauth2Login(oauth2 -> oauth2.loginPage("/login")) // Did this to customize the bad looking static page
                .oauth2Client(withDefaults());
        return http.build();
    }
}
