package com.demo.rest.configuration;

import com.demo.rest.datastore.Datastore;
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

        // [pictures-in-files] get rid of
        ServletContext context = event.getServletContext();
//        String pictureDirPath = context.getInitParameter("pictureDirectory");
//
        event.getServletContext().setAttribute("ownerService", new OwnerService(ownerRepository));

    }

}