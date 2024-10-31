package com.demo.rest.models.breed.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBreedsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Breed {
        private UUID id;
        private String name;
        private int averageLifespan;
        private boolean isHypoallergenic;
    }

    private List<Breed> breeds;
}