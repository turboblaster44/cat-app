package com.demo.rest.models.cat.dto;

import com.demo.rest.models.cat.entity.CatColor;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutCatRequest {
    private String name;
    private CatColor color;
    private Float weight;

}
