package com.demo.rest.models.owner.service;

import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.repository.api.OwnerRepository;
import jakarta.ejb.DuplicateKeyException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class OwnerService {
    private final OwnerRepository repository;

    //probably later add verify password etc.
    @Inject
    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }


    public Optional<Owner> find(UUID id) {
        return repository.find(id);
    }

    public List<Owner> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Owner owner) {
        if (repository.find(owner.getId()).isPresent()){
            throw new IllegalStateException();
        }
        repository.create(owner);
    }

    @Transactional
    public void delete(Owner owner) {
        repository.deleteById(owner.getId());
    }

    @Transactional
    public void update(Owner owner) {
        repository.update(owner);
    }


}
