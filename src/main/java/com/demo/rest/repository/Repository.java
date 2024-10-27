package com.demo.rest.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface  Repository<E,K> {
    Optional<E> find(K id);
    List<E> findAll();
    void create(E entity);
    void delete(E entity);
    void deleteById(UUID id);
    void update(E entity);

}
