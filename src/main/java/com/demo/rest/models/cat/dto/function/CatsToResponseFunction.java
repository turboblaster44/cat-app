package com.demo.rest.models.cat.dto.function;

import com.demo.rest.models.cat.dto.GetCatsResponse;
import com.demo.rest.models.cat.entity.Cat;

import java.util.List;
import java.util.function.Function;

public class CatsToResponseFunction implements Function<List<Cat>, GetCatsResponse> {
    @Override
    public GetCatsResponse apply(List<Cat> entities) {
        return GetCatsResponse.builder()
                .cats(entities.stream()
                        .map(cat -> GetCatsResponse.Cat.builder()
                                .id(cat.getId())
                                .name(cat.getName())
                                .build())
                        .toList())
                .build();
    }
}
