package com.demo.rest.models.owner.dto.functions;

import com.demo.rest.models.owner.dto.GetOwnerResponse;
import com.demo.rest.models.owner.dto.GetOwnersResponse;
import com.demo.rest.models.owner.entity.Owner;

import java.util.Optional;
import java.util.function.Function;

public class OwnerToResponseFunction implements Function<Owner, GetOwnerResponse> {
    @Override
    public GetOwnerResponse apply(Owner owner) {
        return GetOwnerResponse.builder()
                .id(owner.getId())
                .login(owner.getLogin())
                .name(owner.getName())
                .birthDate(owner.getBirthDate())
                .salary(owner.getSalary())
                .roles(owner.getRoles())
                .build();
    }

}
