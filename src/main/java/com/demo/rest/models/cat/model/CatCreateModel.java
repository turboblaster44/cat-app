package com.demo.rest.models.cat.model;

import com.demo.rest.models.breed.model.BreedModel;
import com.demo.rest.models.cat.entity.CatColor;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CatCreateModel {
    private String name;
    private CatColor color;
    private Float weight;

    private BreedModel breed;
}
