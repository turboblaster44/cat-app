package com.demo.rest.models.cat.model.function;

import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.model.CatsModel;

import java.util.List;
import java.util.function.Function;

public class CatsToModelFunction implements Function<List<Cat>, CatsModel> {
    @Override
    public CatsModel apply(List<Cat> entity) {
        return CatsModel.builder()
                .cats(entity.stream()
                        .map(cat -> CatsModel.Cat.builder()
                                .id(cat.getId())
                                .name(cat.getName())
                                .build())
                        .toList())
                .build();
    }
}
