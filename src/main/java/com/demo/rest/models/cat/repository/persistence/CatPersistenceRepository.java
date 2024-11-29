package com.demo.rest.models.cat.repository.persistence;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.repository.api.CatRepository;
import com.demo.rest.models.owner.entity.Owner;
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
public class CatPersistenceRepository implements CatRepository {


    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Cat> find(UUID id) {
        return  Optional.ofNullable(em.find(Cat.class, id));
    }

    @Override
    public List<Cat> findAll() {
        return em.createQuery("select w from Cat w", Cat.class).getResultList();
    }

    @Override
    public void create(Cat entity) {
        em.persist(entity);
        em.refresh(em.find(Breed.class, entity.getBreed().getId()));
        em.refresh(em.find(Owner.class, entity.getOwner().getId()));
    }

    @Override
    public void delete(Cat entity) {
        em.remove(entity);
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(Cat.class, id));
    }

    @Override
    public void update(Cat entity) {
        em.merge(entity);
    }

    @Override
    public Optional<Cat> findByIdAndOwner(UUID id, Owner owner) {
        try {
            return Optional.of(em.createQuery("select w from Cat w where w.id = :id and w.owner = :owner", Cat.class)
                    .setParameter("owner", owner)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Cat> findByBreed(UUID id) {
        return em.createQuery("select w from Cat w where w.breed.id = :breedId", Cat.class)
                .setParameter("breedId", id)
                .getResultList();
    }

    @Override
    public List<Cat> findByOwner(Owner owner) {
        return em.createQuery("select w from Cat w where w.owner = :owner", Cat.class)
                .setParameter("owner", owner)
                .getResultList();
    }

}
