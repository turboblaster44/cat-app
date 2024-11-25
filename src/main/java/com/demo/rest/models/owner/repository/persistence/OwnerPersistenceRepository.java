package com.demo.rest.models.owner.repository.persistence;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.repository.api.OwnerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class OwnerPersistenceRepository implements OwnerRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    @Override
    public Optional<Owner> find(UUID id) {
        return  Optional.ofNullable(em.find(Owner.class, id));
    }

    @Override
    public List<Owner> findAll() {
        return em.createQuery("select w from Owner w", Owner.class).getResultList();
    }

    @Override
    public void create(Owner entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Owner entity) {
        em.remove(entity);
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(Owner.class, id));
    }

    @Override
    public void update(Owner entity) {
        em.merge(entity);
    }

    @Override
    public Optional<Owner> findByLogin(String login) {
        try {
            Owner owner = em.createQuery("SELECT o FROM Owner o WHERE o.login = :login", Owner.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return Optional.ofNullable(owner);
        } catch (NoResultException e) {
            return Optional.empty();
        }    }
}
