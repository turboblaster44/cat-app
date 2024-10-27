package com.demo.rest.models.cat.model.function;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.model.CatCreateModel;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Function;

public class ModelToCatFunction implements Function<CatCreateModel, Cat>, Serializable {

    @Override
    @SneakyThrows
    public Cat apply(CatCreateModel model) {
        return Cat.builder()
                .id(UUID.randomUUID())
                .name(model.getName())
                .weight(model.getWeight())
                .color(model.getColor())
                .breed(Breed.builder()
                        .id(model.getBreed().getId())
                        .build())
                .build();
    }
}
