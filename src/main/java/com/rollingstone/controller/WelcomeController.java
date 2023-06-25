package com.rollingstone.controller;

import com.rollingstone.config.CustomerClient;
import com.rollingstone.config.WelcomeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WelcomeController {

    private final WelcomeClient welcomeClient;

    private final CustomerClient customerClient;

    Logger logger = LoggerFactory.getLogger("WelcomeController");

    public WelcomeController(WelcomeClient welcomeClient, CustomerClient customerClient) {
        this.welcomeClient = welcomeClient;
        this.customerClient = customerClient;
    }

}