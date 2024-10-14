package com.demo.rest.configuration;

import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.service.OwnerService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.util.UUID;

@WebListener
public class InitializeData implements ServletContextListener {
    OwnerService ownerService;


    @Override
    public void contextInitialized(ServletContextEvent event) {
        ownerService = (OwnerService) event.getServletContext().getAttribute("ownerService");
        init();
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @SneakyThrows
    private void init() {
        Owner oskar = Owner.builder()
                .id(UUID.fromString("45a3c22d-77d0-4571-9f4b-4de78b3e5796"))
                .name("oskar")
                .login("turbooskar")
                .salary(10000.0f)
                .birthDate(LocalDate.now())
                .build();
        Owner janek = Owner.builder()
                .id(UUID.fromString("b98f3729-9a89-4ebb-b434-4d2332f5005f"))
                .name("janek")
                .login("turbojanek")
                .salary(15000.0f)
                .birthDate(LocalDate.now())
                .build();
        Owner gogi = Owner.builder()
                .id(UUID.fromString("d80ecfee-a46f-4d05-b02c-8e9664b5982f"))
                .name("kacper")
                .login("turbogogi")
                .salary(100.0f)
                .birthDate(LocalDate.now())
                .build();
        Owner pszemo = Owner.builder()
                .id(UUID.fromString("2f4d3424-8f9b-4e58-bd0a-764bd776ccde"))
                .name("pszemo")
                .login("pszemix")
                .salary(100.0f)
                .birthDate(LocalDate.now())
                .build();

        ownerService.create(oskar);
        ownerService.create(janek);
        ownerService.create(gogi);
        ownerService.create(pszemo);
    }
}
