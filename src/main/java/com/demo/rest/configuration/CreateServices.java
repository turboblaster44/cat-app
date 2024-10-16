package com.demo.rest.configuration;

import com.demo.rest.datastore.Datastore;
import com.demo.rest.models.image.controller.repository.ImageRepository;
import com.demo.rest.models.image.controller.service.ImageService;
import com.demo.rest.models.owner.repository.api.OwnerRepository;
import com.demo.rest.models.owner.repository.memory.OwnerInMemoryRepository;
import com.demo.rest.models.owner.service.OwnerService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CreateServices implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        Datastore dataSource = (Datastore) event.getServletContext().getAttribute("datasource");

        OwnerRepository ownerRepository = new OwnerInMemoryRepository(dataSource);
        ImageRepository imageRepository = new ImageRepository(dataSource);

        event.getServletContext().setAttribute("ownerService", new OwnerService(ownerRepository));
        event.getServletContext().setAttribute("imageService", new ImageService(imageRepository));

    }

}