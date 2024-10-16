package com.demo.rest.models.image.controller.api;

import java.util.UUID;

public interface ImageController {
    byte[] getImage(UUID id);

    void putImage(UUID id, byte[] imageBytes);

    void createImage(UUID id, byte[] imageBytes);

    void deleteImage(UUID id);

}
