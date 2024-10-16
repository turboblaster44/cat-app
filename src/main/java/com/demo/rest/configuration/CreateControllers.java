package com.demo.rest.configuration;

import com.demo.rest.models.image.controller.api.ImageController;
import com.demo.rest.models.image.controller.implementation.ImageControllerImplementation;
import com.demo.rest.models.image.controller.service.ImageService;
import com.demo.rest.models.owner.controller.implementation.OwnerControllerImplementation;
import com.demo.rest.models.owner.service.OwnerService;
import com.demo.rest.utils.DtoFunctionFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.awt.*;

public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        OwnerService ownerService = (OwnerService) event.getServletContext().getAttribute("ownerService");
        ImageService imageService = (ImageService) event.getServletContext().getAttribute("imageService");

        event.getServletContext().setAttribute("ownerController", new OwnerControllerImplementation(
                ownerService,
                new DtoFunctionFactory()
        ));
        event.getServletContext().setAttribute("imageController", new ImageControllerImplementation(imageService));
    }
}

