package com.demo.rest.models.cat.repository.implementation;

import com.demo.rest.datastore.Datastore;
import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.repository.api.CatRepository;
import com.demo.rest.models.owner.entity.Owner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CatRepositoryImplementation implements CatRepository {

    private final Datastore store;

    @Inject
    public CatRepositoryImplementation(Datastore store) {
        this.store = store;
    }

    @Override
    public Optional<Cat> find(UUID id) {
        return store.findAllCats().stream()
                .filter(cat -> cat.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Cat> findByBreed(Breed breed) {
        return store.findAllCats().stream()
                .filter(cat -> cat.getBreed().getId().equals(breed.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cat> findByOwner(Owner owner) {
        return store.findAllCats().stream()
                .filter(cat -> cat.getOwner().getId().equals(owner.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cat> findAll() {
        return store.findAllCats();
    }

    @Override
    public void create(Cat entity) {
        store.createCat(entity);
    }

    @Override
    public void delete(Cat entity) {
        store.deleteCat(entity);
    }

    @Override
    public void update(Cat entity) {
        store.updateCat(entity);
    }
}