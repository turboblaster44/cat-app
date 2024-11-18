package com.demo.rest.models.breed.entity;

import com.demo.rest.models.cat.entity.Cat;
import jakarta.persistence.*;
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
@Entity
@Table(name = "breeds")
public class Breed implements Serializable {

    @Id
    private UUID id;
    private String name;
    private int averageLifespan;
    private boolean isHypoallergenic;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "breed", cascade = CascadeType.REMOVE)
    private List<Cat> cats;

}
