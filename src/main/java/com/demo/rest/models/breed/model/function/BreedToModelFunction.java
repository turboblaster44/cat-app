package com.demo.rest.models.breed.model.function;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.model.BreedModel;

import java.util.List;
import java.util.function.Function;

public class BreedToModelFunction implements Function<Breed, BreedModel> {

    @Override
    public BreedModel apply(Breed entity) {
        return BreedModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .averageLifespan(entity.getAverageLifespan())
                .hypoallergenic(entity.isHypoallergenic())
                .build();
    }
}
