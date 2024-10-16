package com.demo.rest.models.image.controller.implementation;

import com.demo.rest.controller.servlet.exception.NotFoundException;
import com.demo.rest.models.image.controller.api.ImageController;
import com.demo.rest.models.image.controller.service.ImageService;
import com.demo.rest.models.owner.entity.Owner;

import java.util.UUID;

public class ImageControllerImplementation implements ImageController {
    private final ImageService imageService;

    public ImageControllerImplementation(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public byte[] getImage(UUID id) {
        return this.imageService.getImage(id);
    }

    @Override
    public void putImage(UUID id, byte[] imageBytes) {
        try {
            imageService.getImage(id);
            System.out.println("updating image");
            this.imageService.updateImage(id, imageBytes);
        } catch (NotFoundException e) {
            System.out.println("creating image");
            this.imageService.createImage(id, imageBytes);
        }
    }

    @Override
    public void createImage(UUID id, byte[] imageBytes) {
        this.imageService.createImage(id, imageBytes);

    }

    @Override
    public void deleteImage(UUID id) {
        this.imageService.deleteImage(id);

    }

}
