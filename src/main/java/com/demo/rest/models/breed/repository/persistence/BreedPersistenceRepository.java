package com.demo.rest.models.breed.repository.persistence;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.repository.api.BreedRepository;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.repository.api.CatRepository;
import com.demo.rest.models.owner.entity.Owner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class BreedPersistenceRepository implements BreedRepository {


    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    @Override
    public Optional<Breed> find(UUID id) {
        return  Optional.ofNullable(em.find(Breed.class, id));
    }

    @Override
    public List<Breed> findAll() {
        return em.createQuery("select w from Breed w", Breed.class).getResultList();
    }

    @Override
    public void create(Breed entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Breed entity) {
        em.remove(entity);
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(Breed.class, id));
    }

    @Override
    public void update(Breed entity) {
        em.merge(entity);
    }
}