package com.demo.rest.models.breed.model.function;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.model.BreedsModel;

import java.util.List;
import java.util.function.Function;

public class BreedsToModelFunction implements Function<List<Breed>, BreedsModel> {

    @Override
    public BreedsModel apply(List<Breed> entity) {
        return BreedsModel.builder()
                .breeds(entity.stream()
                        .map(breed -> BreedsModel.Breed.builder()
                                .id(breed.getId())
                                .name(breed.getName())
                                .build())
                        .toList())
                .build();
    }
}
