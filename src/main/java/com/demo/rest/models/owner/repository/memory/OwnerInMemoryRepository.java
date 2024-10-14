package com.demo.rest.models.owner.repository.memory;

import com.demo.rest.datastore.Datastore;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.repository.api.OwnerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OwnerInMemoryRepository implements OwnerRepository {

    private final Datastore store;

    public OwnerInMemoryRepository(Datastore store) {
        this.store = store;
    }


    @Override
    public Optional<Owner> find(UUID id) {
        return store.findAllOwners().stream()
                .filter(owner -> owner.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Owner> findAll() {
        return store.findAllOwners();
    }

    @Override
    public void create(Owner entity) {
        store.createOwner(entity);
    }

    @Override
    public void delete(Owner entity) {
        store.deleteOwner(entity);
    }

    @Override
    public void update(Owner entity) {
        store.updateOwner(entity);
    }

    @Override
    public Optional<Owner> findByLogin(String login) {
        return store.findOwnerByLogin(login);
    }
}
