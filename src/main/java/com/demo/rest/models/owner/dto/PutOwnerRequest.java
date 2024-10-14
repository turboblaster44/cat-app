package com.demo.rest.models.owner.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutOwnerRequest {
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private Float salary;
    private String login;
}