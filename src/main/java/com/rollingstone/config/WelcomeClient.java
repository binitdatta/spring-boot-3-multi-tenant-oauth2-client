package com.rollingstone.config;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@HttpExchange("http://localhost:14040")
public interface WelcomeClient {
    @GetExchange("/secure/multitenant/hello")
    String sayHello();
}
