package com.demo.rest.models.cat.repository.api;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatRepository extends Repository<Cat, UUID> {

    List<Cat> findByBreed(Breed breed);

    List<Cat> findByOwner(Owner owner);

}
