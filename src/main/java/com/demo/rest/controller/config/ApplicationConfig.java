package com.demo.rest.controller.config;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api")//Can be overwritten in web.xml using servlet configuration.
public class ApplicationConfig extends Application {

    @PostConstruct
    public void init() {
        System.out.println("JAX-RS application started with /api base path!");
    }
}
