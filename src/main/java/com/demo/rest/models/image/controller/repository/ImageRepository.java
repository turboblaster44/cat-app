package com.demo.rest.models.image.controller.repository;

import com.demo.rest.datastore.Datastore;

import java.util.UUID;

public class ImageRepository {
    private final Datastore store;

    public ImageRepository(Datastore store) {
        this.store = store;
    }

    public byte[] getImage(UUID id) {
        return this.store.getImage(id);
    }

    public void updateImage(UUID id, byte[] imageBytes) {
        this.store.updateImage(id, imageBytes);
    }

    public void createImage(UUID id, byte[] imageBytes) {
        this.store.createImage(id, imageBytes);
    }

    public void deleteImage(UUID id) {
        this.store.deleteImage(id);
    }

}
