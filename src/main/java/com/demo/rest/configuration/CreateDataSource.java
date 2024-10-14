package com.demo.rest.configuration;

import com.demo.rest.datastore.Datastore;
import com.demo.rest.utils.CloningUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


public class CreateDataSource implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("datasource", new Datastore(new CloningUtil()));
    }

}