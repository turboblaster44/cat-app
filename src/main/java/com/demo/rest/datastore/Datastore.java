package com.demo.rest.datastore;

import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.utils.CloningUtil;
import lombok.extern.java.Log;

import java.util.*;
import java.util.stream.Collectors;

@Log
public class Datastore {

    private final CloningUtil cloningUtil;

    private Set<Owner> owners = new HashSet<>();

    public Datastore(CloningUtil cloningUtility) {
        this.cloningUtil = cloningUtility;
    }

    public synchronized List<Owner> findAllOwners() {
        return owners.stream()
                .map(cloningUtil::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Owner> findOwnerByLogin(String login) {
        return owners.stream()
                .filter(owner -> owner.getLogin().equals(login))
                .findFirst()
                .map(cloningUtil::clone);
    }

    public synchronized void createOwner(Owner value) throws IllegalArgumentException {
        if (owners.stream().anyMatch(owner -> owner.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The owner id \"%s\" is not unique".formatted(value.getId()));
        }
        owners.add(cloningUtil.clone(value));
    }

    public synchronized void deleteOwner(Owner value) {
        if (!owners.removeIf(owner -> owner.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("No owner found with id: " + value.getId());
        }
    }

    public synchronized void updateOwner(Owner value) throws IllegalArgumentException {
        if (owners.removeIf(owner -> owner.getId().equals(value.getId()))) {
            owners.add(cloningUtil.clone(value));
        } else {
            throw new IllegalArgumentException("The owner with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

}
