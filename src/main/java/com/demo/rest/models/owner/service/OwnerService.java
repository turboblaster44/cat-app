package com.demo.rest.models.owner.service;

import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.entity.OwnerRoles;
import com.demo.rest.models.owner.repository.api.OwnerRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.DuplicateKeyException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class OwnerService {
    private final OwnerRepository repository;
    private final Pbkdf2PasswordHash passwordHash;

    //probably later add verify password etc.
    @Inject
    public OwnerService(OwnerRepository repository,
                        @SuppressWarnings("CdiInjectionPointsInspection") Pbkdf2PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
        this.repository = repository;
    }

    @RolesAllowed(OwnerRoles.ADMIN)
    public Optional<Owner> find(UUID id) {
        return repository.find(id);
    }

    @RolesAllowed(OwnerRoles.ADMIN)
    public Optional<Owner> findByLogin(String login) {
        return repository.findByLogin(login);
    }

//    @RolesAllowed(OwnerRoles.ADMIN)
    @RolesAllowed(OwnerRoles.ADMIN)
    public List<Owner> findAll() {
        return repository.findAll();
    }

    @PermitAll
    public void create(Owner owner) {
        if (repository.find(owner.getId()).isPresent()) {
            throw new IllegalArgumentException("owner already exists.");
        }

        if (owner.getRoles() == null || owner.getRoles().isEmpty()) {
            owner.setRoles(List.of(OwnerRoles.OWNER));
        }
        owner.setPassword(passwordHash.generate(owner.getPassword().toCharArray()));
        repository.create(owner);
    }

    @PermitAll
    public boolean verifyPassword(String login, String password) {
        return findByLogin(login)
                .map(owner -> passwordHash.verify(password.toCharArray(), owner.getPassword()))
                .orElse(false);
    }

    @RolesAllowed(OwnerRoles.ADMIN)
    public void delete(Owner owner) {
        repository.deleteById(owner.getId());
    }

    @RolesAllowed(OwnerRoles.ADMIN)
    public void update(Owner owner) {
        repository.update(owner);
    }


}
