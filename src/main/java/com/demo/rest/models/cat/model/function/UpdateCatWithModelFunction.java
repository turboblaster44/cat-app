package com.demo.rest.models.cat.model.function;

import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.model.CatEditModel;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.function.BiFunction;

public class UpdateCatWithModelFunction implements BiFunction<Cat, CatEditModel, Cat>, Serializable {

    @Override
    @SneakyThrows
    public Cat apply(Cat entity, CatEditModel request) {
        return Cat.builder()
                .id(entity.getId())
                .name(request.getName())
                .color(request.getColor())
                .weight(request.getWeight())
                .breed(entity.getBreed())
                .build();
    }
}
