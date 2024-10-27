package com.demo.rest.models.breed.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class BreedModel {
    private UUID id;

    private String name;
    private int averageLifespan;
    private boolean hypoallergenic;
}
