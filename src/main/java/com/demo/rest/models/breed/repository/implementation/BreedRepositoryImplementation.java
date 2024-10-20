package com.demo.rest.models.breed.repository.implementation;

import com.demo.rest.datastore.Datastore;
import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.repository.api.BreedRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class BreedRepositoryImplementation implements BreedRepository {

    private final Datastore store;

    @Inject
    public BreedRepositoryImplementation(Datastore store) {
        this.store = store;
    }

    @Override
    public Optional<Breed> find(UUID id) {
        return store.findAllBreeds().stream()
                .filter(breed -> breed.getId().equals(id))
                .findFirst();
    }
    @Override
    public List<Breed> findAll() {
        return store.findAllBreeds();
    }

    @Override
    public void create(Breed entity) {
        store.createBreed(entity);
    }

    @Override
    public void delete(Breed entity) {
        store.deleteBreed(entity);
    }

    @Override
    public void update(Breed entity) {
        store.updateBreed(entity);
    }
}
