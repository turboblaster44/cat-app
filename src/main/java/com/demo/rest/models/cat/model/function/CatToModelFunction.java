package com.demo.rest.models.cat.model.function;

import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.model.CatModel;
import com.demo.rest.models.cat.model.CatsModel;

import java.io.Serializable;
import java.util.function.Function;

public class CatToModelFunction implements Function<Cat, CatModel>, Serializable {
    @Override
    public CatModel apply(Cat entity) {
        return CatModel.builder()
                .name(entity.getName())
                .color(entity.getColor())
                .weight(entity.getWeight())
                .build();
    }
}
