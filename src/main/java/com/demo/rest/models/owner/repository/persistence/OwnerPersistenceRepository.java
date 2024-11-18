package com.demo.rest.models.owner.repository.persistence;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.repository.api.OwnerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
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
}
