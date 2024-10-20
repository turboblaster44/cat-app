package com.demo.rest.models.image.repository.api;

import java.util.UUID;

public interface ImageRepository {
    byte[] getImage(UUID id);

    void updateImage(UUID id, byte[] imageBytes);

    void createImage(UUID id, byte[] imageBytes);

    void deleteImage(UUID id);
}
