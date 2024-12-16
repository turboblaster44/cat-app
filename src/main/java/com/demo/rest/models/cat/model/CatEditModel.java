package com.demo.rest.models.cat.model;

import com.demo.rest.models.cat.entity.CatColor;
import com.demo.rest.models.owner.model.OwnerModel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CatEditModel {
    private String name;
    private CatColor color;
    private Float weight;
    private OwnerModel owner;
    private Long version;
}
