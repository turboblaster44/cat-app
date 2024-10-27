package com.demo.rest.models.cat.model;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.CatColor;
import com.demo.rest.models.owner.entity.Owner;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CatModel {
    private String name;
    private CatColor color;
    private Float weight;
}
