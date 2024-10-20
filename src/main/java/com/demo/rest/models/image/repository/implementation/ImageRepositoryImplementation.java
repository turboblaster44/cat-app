package com.demo.rest.models.image.repository.implementation;

import com.demo.rest.datastore.Datastore;
import com.demo.rest.models.image.repository.api.ImageRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class ImageRepositoryImplementation implements ImageRepository {
    private final Datastore store;

    @Inject
    public ImageRepositoryImplementation(Datastore store) {
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
