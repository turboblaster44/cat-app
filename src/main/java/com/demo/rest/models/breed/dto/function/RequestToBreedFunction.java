package com.demo.rest.models.breed.dto.function;

import com.demo.rest.models.breed.dto.PutBreedRequest;
import com.demo.rest.models.breed.entity.Breed;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToBreedFunction implements BiFunction<UUID, PutBreedRequest, Breed> {


    @Override
    public Breed apply(UUID id, PutBreedRequest request) {
        return Breed.builder()
                .id(id)
                .name(request.getName())
                .averageLifespan(request.getAverageLifespan())
                .isHypoallergenic(request.isHypoallergenic())
                .build();
    }
}
