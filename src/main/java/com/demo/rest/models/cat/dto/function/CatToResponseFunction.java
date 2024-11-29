package com.demo.rest.models.cat.dto.function;

import com.demo.rest.models.cat.dto.GetCatResponse;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.owner.dto.GetOwnersResponse;

import java.util.function.Function;

public class CatToResponseFunction implements Function<Cat, GetCatResponse> {

    @Override
    public GetCatResponse apply(Cat entity) {
        return GetCatResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .weight(entity.getWeight())
                .color(entity.getColor())
                .breed(GetCatResponse.Breed.builder()
                        .id(entity.getBreed().getId())
                        .name(entity.getBreed().getName())
                        .build())
                .owner(GetCatResponse.Owner.builder()
                        .id(entity.getOwner().getId())
                        .name(entity.getOwner().getName())
                        .build())
                .build();
    }

}

