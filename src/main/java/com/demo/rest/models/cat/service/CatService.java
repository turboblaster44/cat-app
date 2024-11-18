package com.demo.rest.models.cat.service;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.repository.api.CatRepository;
import com.demo.rest.models.owner.entity.Owner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class CatService {
    private final CatRepository catRepository;

    @Inject
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Optional<Cat> find(UUID id) {
        return catRepository.find(id);
    }

    public List<Cat> findByBreedId(UUID id) {
        return catRepository.findByBreed(id);
    }

    public List<Cat> findByOwner(Owner owner) {
        return catRepository.findByOwner(owner);
    }

    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    @Transactional
    public void create(Cat cat) {
        catRepository.create(cat);
    }

    @Transactional
    public void put(Cat cat) {
        if (catRepository.find(cat.getId()).isEmpty())
            catRepository.create(cat);
        else
            catRepository.update(cat);
    }

    @Transactional
    public void delete(Cat cat) {
        catRepository.delete(cat);
    }

    @Transactional
    public void deleteById(UUID id) {
        catRepository.deleteById(id);
    }

    @Transactional
    public void update(Cat cat) {
        catRepository.update(cat);
    }

}
