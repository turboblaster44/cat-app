package com.demo.rest.models.cat.model.function;

import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.model.CatEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class CatToCatEditModelFunction implements Function<Cat, CatEditModel>, Serializable {

    @Override
    public CatEditModel apply(Cat entity) {
        return CatEditModel.builder()
                .name(entity.getName())
                .color(entity.getColor())
                .weight(entity.getWeight())
                .build();
    }
}
