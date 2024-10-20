package com.demo.rest.models.breed.entity;

import com.demo.rest.models.cat.entity.Cat;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Breed implements Serializable {
    private UUID id;
    private String name;
    private int averageLifespan;
    private boolean isHypoallergenic;
    @Singular
    private List<Cat> cats;
}
