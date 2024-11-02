package com.demo.rest.models.cat.dto.function;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.models.cat.dto.PutCatRequest;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.utils.TriFunction;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

public class RequestToCatFunction implements TriFunction<UUID, UUID, PutCatRequest, Cat> {

    @Override
    public Cat apply(UUID breedId, UUID catId, PutCatRequest request) {
        return Cat.builder()
                .id(catId)
                .name(request.getName())
                .weight(request.getWeight())
                .color(request.getColor())
                .breed(Breed.builder()
                        .id(breedId)
                        .build())
                .build();
    }
}
