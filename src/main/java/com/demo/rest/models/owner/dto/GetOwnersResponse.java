package com.demo.rest.models.owner.dto;

import lombok.*;

import java.util.List;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetOwnersResponse {
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
        private LocalDate birthDate;
        private Float salary;
        private String login;
    }

    @Singular
    private List<Owner> owners;
}
