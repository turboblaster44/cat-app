package com.demo.rest.models.breed.dto.funcion;

import com.demo.rest.models.breed.dto.GetBreedsResponse;
import com.demo.rest.models.breed.entity.Breed;

import java.util.List;
import java.util.function.Function;

public class BreedsToResponseFunction implements Function<List<Breed>, GetBreedsResponse> {
    @Override
    public GetBreedsResponse apply(List<Breed> entities) {
        return GetBreedsResponse.builder()
                .breeds(entities.stream()
                        .map(breed -> GetBreedsResponse.Breed.builder()
                                .id(breed.getId())
                                .name(breed.getName())
                                .averageLifespan(breed.getAverageLifespan())
                                .isHypoallergenic(breed.isHypoallergenic())
                                .build())
                        .toList())
                .build();
    }
}
