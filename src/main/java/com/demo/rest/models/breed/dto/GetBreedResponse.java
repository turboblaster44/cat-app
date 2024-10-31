package com.demo.rest.models.breed.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBreedResponse {
    private UUID id;
    private String name;
    private int averageLifespan;
    private boolean isHypoallergenic;
}
