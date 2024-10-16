package com.demo.rest.models.image.controller.service;

import com.demo.rest.models.image.controller.repository.ImageRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public byte[] getImage(UUID uuid) {
        return this.imageRepository.getImage(uuid);
    }

    public void updateImage(UUID id, byte[] imageBytes) {
        this.imageRepository.updateImage(id, imageBytes);
    }

    public void createImage(UUID uuid, byte[] imageBytes) {
        this.imageRepository.createImage(uuid, imageBytes);
    }

    public void deleteImage(UUID uuid) {
        this.imageRepository.deleteImage(uuid);
    }


}