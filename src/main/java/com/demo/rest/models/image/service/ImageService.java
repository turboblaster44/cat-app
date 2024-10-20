package com.demo.rest.models.image.service;

import com.demo.rest.models.image.repository.api.ImageRepository;
import com.demo.rest.models.image.repository.implementation.ImageRepositoryImplementation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class ImageService {
    private final ImageRepository imageRepository;

    @Inject
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