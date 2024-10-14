package com.demo.rest.models.owner.dto;

import com.demo.rest.models.cat.entity.Cat;
import lombok.*;

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
public class GetOwnerResponse {
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private Float salary;
    private String login;

}
