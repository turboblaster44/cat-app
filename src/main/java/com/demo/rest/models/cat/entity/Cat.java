package com.demo.rest.models.cat.entity;

import com.demo.rest.entity.VersionAndCreationDateAuditable;
import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.owner.entity.Owner;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
@Table(name = "cats")
public class Cat extends VersionAndCreationDateAuditable implements Serializable {

    @Id
    private UUID id;

    private String name;
    private CatColor color;
    private Float weight;

    @ManyToOne
    @JoinColumn(name = "breed")
    private Breed breed;

    @ManyToOne
    @JoinColumn(name = "owner")
    private Owner owner;

}
