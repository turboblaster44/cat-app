package com.demo.rest.models.cat.service;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.repository.api.CatRepository;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.entity.OwnerRoles;
import com.demo.rest.models.owner.repository.api.OwnerRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;
    private final SecurityContext securityContext;

    @Inject
    public CatService(CatRepository catRepository, @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.securityContext = securityContext;
        this.ownerRepository = ownerRepository;
    }


    @RolesAllowed(OwnerRoles.OWNER)
    public Optional<Cat> find(UUID id) {
        Optional<Cat> optCat = catRepository.find(id);
        checkAdminRoleOrOwner(optCat);
        return optCat;
    }

    @RolesAllowed(OwnerRoles.OWNER)
    public Optional<Cat> find(Owner owner, UUID id) {
        return catRepository.findByIdAndOwner(id, owner);
    }

    @RolesAllowed(OwnerRoles.OWNER)
    public List<Cat> findByBreedId(UUID id) {
        if (securityContext.isCallerInRole(OwnerRoles.ADMIN)) {
            return catRepository.findByBreed(id);
        }
        return catRepository.findByBreedAndOwner(id, ownerRepository.findByLogin(securityContext.getCallerPrincipal().getName()).get());
    }

    @RolesAllowed(OwnerRoles.OWNER)
    public List<Cat> findByOwner(Owner owner) {
        return catRepository.findByOwner(owner);
    }

    @RolesAllowed(OwnerRoles.OWNER)
    public List<Cat> findAll() {
        return findAllForCallerPrincipal();
    }

    @RolesAllowed(OwnerRoles.OWNER)
    public List<Cat> findAll(Owner owner) {
        return catRepository.findByOwner(owner);
    }

    @RolesAllowed(OwnerRoles.OWNER)
    private List<Cat> findAllForCallerPrincipal() {
        if (securityContext.isCallerInRole(OwnerRoles.ADMIN)) {
            //return findAll();
            return catRepository.findAll();
        }
        System.out.println(securityContext.getCallerPrincipal().getName());
        Owner owner = ownerRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return findAll(owner);
    }


    @RolesAllowed(OwnerRoles.OWNER)
    public Optional<Cat> findForCallerPrincipal(UUID id) {
        if (securityContext.isCallerInRole(OwnerRoles.ADMIN)) {
            return find(id);
        }
        Owner owner = ownerRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return find(owner, id);
    }


    @RolesAllowed(OwnerRoles.ADMIN)
    public void create(Cat cat) {
        catRepository.create(cat);
    }


    @RolesAllowed(OwnerRoles.OWNER)
    public void createForCallerPrincipal(Cat cat) {
        System.out.println(cat);
        if (cat.getOwner().getId() == null) {
            Owner owner = ownerRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                    .orElseThrow(IllegalStateException::new);
            cat.setOwner(owner);
        }
        put(cat);
    }

    public void put(Cat cat) {
        if (catRepository.find(cat.getId()).isEmpty())
            catRepository.create(cat);
        else
            catRepository.update(cat);
    }

    @RolesAllowed(OwnerRoles.OWNER)
    public void delete(Cat cat) {
        checkAdminRoleOrOwner(catRepository.find(cat.getId()));
        catRepository.delete(cat);
    }

    @RolesAllowed(OwnerRoles.OWNER)
    public void deleteById(UUID id) {
        checkAdminRoleOrOwner(catRepository.find(id));
        catRepository.deleteById(id);
    }

    @RolesAllowed(OwnerRoles.OWNER)
    public void update(Cat cat) {
        checkAdminRoleOrOwner(catRepository.find(cat.getId()));
        catRepository.update(cat);
    }

    private void checkAdminRoleOrOwner(Optional<Cat> cat) throws EJBAccessException {
        if (securityContext.isCallerInRole(OwnerRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(OwnerRoles.OWNER)
                && cat.isPresent()
                && cat.get().getOwner().getLogin().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }

}
