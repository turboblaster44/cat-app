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
    private String name;
    private LocalDate birthDate;
    private Float salary;
    private String login;
    private String password;
}