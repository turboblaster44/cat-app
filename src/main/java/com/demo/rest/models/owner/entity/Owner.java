package com.demo.rest.models.owner.entity;

import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.entity.CatColor;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Owner implements Serializable {

    private UUID id;

    private String name;
    private LocalDate birthDate;
    private Float salary;
    private List<Cat> cats;

    private String login;
}
