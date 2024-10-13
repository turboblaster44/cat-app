package com.demo.rest.models.breed.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Breed {
    private String name;
    private int averageLifespan;
    private boolean isHypoallergenic;
}
