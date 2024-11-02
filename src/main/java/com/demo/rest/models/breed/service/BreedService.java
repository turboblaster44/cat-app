package com.demo.rest.models.breed.service;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.repository.api.BreedRepository;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.repository.api.CatRepository;
import com.demo.rest.models.owner.entity.Owner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class BreedService {
    private final BreedRepository breedRepository;
    private final CatRepository catRepository;

    @Inject
    public BreedService(BreedRepository breedRepository, CatRepository catRepository) {
        this.breedRepository = breedRepository;
        this.catRepository = catRepository;
    }

    public Optional<Breed> find(UUID id) {
        return breedRepository.find(id);
    }

    public List<Breed> findAll() {
        return breedRepository.findAll();
    }

    public void create(Breed breed) {
        breedRepository.create(breed);
    }

    public void put(Breed breed) {
        if (breedRepository.find(breed.getId()).isEmpty())
            breedRepository.create(breed);
        else
            breedRepository.update(breed);
    }



    public void delete(Breed breed) {
        breedRepository.delete(breed);
        catRepository.deleteByBreedId(breed.getId());
    }

    public void deleteById(UUID id) {
        breedRepository.deleteById(id);
        catRepository.deleteByBreedId(id);
    }

    public void update(Breed breed) {
        breedRepository.update(breed);
    }

}
