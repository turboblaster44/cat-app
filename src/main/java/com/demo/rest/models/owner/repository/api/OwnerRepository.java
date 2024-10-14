package com.demo.rest.models.owner.repository.api;

import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository extends Repository<Owner, UUID> {
    Optional<Owner> findByLogin(String login);
}
