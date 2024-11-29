package com.demo.rest.models.cat.dto;

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
public class GetCatResponse {
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
    }
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Owner {
        private UUID id;
        private String name;
    }

    private UUID id;
    private String name;
    private CatColor color;
    private Float weight;

    private Breed breed;
    private Owner owner;
}
