package com.demo.rest.configuration;

import com.demo.rest.datastore.Datastore;
import com.demo.rest.utils.CloningUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.nio.file.Path;
import java.nio.file.Paths;


public class CreateDataSource implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        Path imageDir = Paths.get(event.getServletContext().getInitParameter("imageDir"));
        event.getServletContext().setAttribute("datasource", new Datastore(new CloningUtil(), imageDir));
    }

}