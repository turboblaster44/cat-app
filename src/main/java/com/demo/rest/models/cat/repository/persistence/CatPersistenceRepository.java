package com.demo.rest.models.cat.repository.persistence;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.entity.Breed_;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.entity.Cat_;
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
        Root<Cat> root = query.from(Cat.class);
        query.select(root).where(cb.equal(root.get(Cat_.id), id));

        try {
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Cat> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
        Root<Cat> root = query.from(Cat.class);
        query.select(root);
        return em.createQuery(query).getResultList();
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Cat> delete = cb.createCriteriaDelete(Cat.class);
        Root<Cat> root = delete.from(Cat.class);
        delete.where(cb.equal(root.get(Cat_.id), id));
        em.createQuery(delete).executeUpdate();
    }

    @Override
    public void update(Cat entity) {
        em.merge(entity);
    }

    @Override
    public Optional<Cat> findByIdAndOwner(UUID id, Owner owner) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
            Root<Cat> root = query.from(Cat.class);
            query.select(root)
                    .where(cb.equal(root.get(Cat_.id), id), cb.equal(root.get(Cat_.owner), owner));

            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Cat> findByBreed(UUID id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
        Root<Cat> root = query.from(Cat.class);
        query.select(root).where(cb.equal(root.get(Cat_.breed).get(Breed_.id), id));

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Cat> findByOwner(Owner owner) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
        Root<Cat> root = query.from(Cat.class);
        query.select(root).where(cb.equal(root.get(Cat_.owner), owner));

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Cat> findByBreedAndOwner(UUID breedId, Owner owner) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
        Root<Cat> root = query.from(Cat.class);
        query.select(root)
                .where(cb.equal(root.get(Cat_.owner), owner), cb.equal(root.get(Cat_.breed).get(Breed_.id), breedId));

        return em.createQuery(query).getResultList();
    }

}
