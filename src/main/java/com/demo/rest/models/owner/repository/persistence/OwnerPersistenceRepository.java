package com.demo.rest.models.owner.repository.persistence;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.entity.Owner_;
import com.demo.rest.models.owner.repository.api.OwnerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Owner> query = cb.createQuery(Owner.class);
        Root<Owner> root = query.from(Owner.class);
        query.select(root);
        return em.createQuery(query).getResultList();
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Owner> query = cb.createQuery(Owner.class);
            Root<Owner> root = query.from(Owner.class);
            query.select(root)
                    .where(cb.equal(root.get(Owner_.login), login));  // Accessing login using Owner_
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }    }
}
