package com.demo.rest.models.breed.repository.persistence;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.entity.Breed_;
import com.demo.rest.models.breed.repository.api.BreedRepository;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.repository.api.CatRepository;
import com.demo.rest.models.owner.entity.Owner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class BreedPersistenceRepository implements BreedRepository {


    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    @Override
    public Optional<Breed> find(UUID id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Breed> query = cb.createQuery(Breed.class);
        Root<Breed> root = query.from(Breed.class);
        query.select(root).where(cb.equal(root.get(Breed_.id), id));

        try {
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Breed> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Breed> query = cb.createQuery(Breed.class);
        Root<Breed> root = query.from(Breed.class);
        query.select(root);
        return em.createQuery(query).getResultList();
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Breed> delete = cb.createCriteriaDelete(Breed.class);
        Root<Breed> root = delete.from(Breed.class);
        delete.where(cb.equal(root.get(Breed_.id), id));
        em.createQuery(delete).executeUpdate();
    }

    @Override
    public void update(Breed entity) {
        em.merge(entity);
    }
}