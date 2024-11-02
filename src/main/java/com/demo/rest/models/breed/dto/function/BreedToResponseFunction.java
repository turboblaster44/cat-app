package com.demo.rest.models.breed.dto.function;

import com.demo.rest.models.breed.dto.GetBreedResponse;
import com.demo.rest.models.breed.entity.Breed;

import java.util.function.Function;

public class BreedToResponseFunction implements Function<Breed, GetBreedResponse> {
    @Override
    public GetBreedResponse apply(Breed breed) {
        return GetBreedResponse.builder()
                .id(breed.getId())
                .name(breed.getName())
                .averageLifespan(breed.getAverageLifespan())
                .isHypoallergenic(breed.isHypoallergenic())
                .build();
    }
}
