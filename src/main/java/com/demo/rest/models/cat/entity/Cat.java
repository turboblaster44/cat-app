package com.demo.rest.models.cat.entity;

import com.demo.rest.models.breed.entity.Breed;
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
public class Cat {

    private UUID id;

    private String name;
    private CatColor color;
    private Float weight;

    private Breed breed;
    private Owner owner;
}
