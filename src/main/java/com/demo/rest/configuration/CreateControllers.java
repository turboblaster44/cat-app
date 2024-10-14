package com.demo.rest.configuration;

import com.demo.rest.models.owner.controller.implementation.OwnerControllerImplementation;
import com.demo.rest.models.owner.service.OwnerService;
import com.demo.rest.utils.DtoFunctionFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        OwnerService ownerService = (OwnerService) event.getServletContext().getAttribute("ownerService");

        event.getServletContext().setAttribute("ownerController", new OwnerControllerImplementation(
                ownerService,
                new DtoFunctionFactory()
        ));
    }
}

