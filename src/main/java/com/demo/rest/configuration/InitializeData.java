package com.demo.rest.configuration;

import com.demo.rest.models.image.controller.service.ImageService;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.service.OwnerService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@WebListener
public class InitializeData implements ServletContextListener {
    private OwnerService ownerService;
    private ImageService imageService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        Path imageDir = Paths.get(event.getServletContext().getInitParameter("imageDir"));
        ownerService = (OwnerService) event.getServletContext().getAttribute("ownerService");
        imageService = (ImageService) event.getServletContext().getAttribute("imageService");
        initImageDir(imageDir);
        init();
    }

    private void initImageDir(Path imageDir) {
        try {
            Files.createDirectories(imageDir);
            System.out.println("directory created: " + imageDir.toAbsolutePath());
            //clear directory
            Files.walk(imageDir)
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            System.err.println("Could not delete file: " + file);
                        }
                    });

        } catch (IOException e) {
            throw new IllegalStateException("exception when creating picture directory", e);
        }
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

        imageService.createImage(oskar.getId(), getImageBytes(oskar.getId()));
        imageService.createImage(janek.getId(), getImageBytes(janek.getId()));
        imageService.createImage(gogi.getId(), getImageBytes(gogi.getId()));
        imageService.createImage(pszemo.getId(), getImageBytes(pszemo.getId()));


        ownerService.create(oskar);
        ownerService.create(janek);
        ownerService.create(gogi);
        ownerService.create(pszemo);
    }

    private byte[] getImageBytes(UUID id) throws IOException {
        Path resPath = Paths.get("C:\\Users\\micha\\OneDrive\\Pulpit\\GIT REPOS\\cat-app\\src\\main\\res\\" + id.toString() + ".png").normalize();
        return Files.readAllBytes(resPath);
    }
}
