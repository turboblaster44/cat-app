package com.demo.rest.models.owner.service;

import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.repository.api.OwnerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OwnerService {
    private final OwnerRepository repository;

    //probably later add verify password etc.
    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }


    public Optional<Owner> find(UUID id) {
        return repository.find(id);
    }

    public List<Owner> findAll() {
        return repository.findAll();
    }

    public void create(Owner owner) {
        repository.create(owner);
    }

    public void delete(Owner owner) {
        repository.delete(owner);
    }

    public void update(Owner owner) {
        repository.update(owner);
    }


}
