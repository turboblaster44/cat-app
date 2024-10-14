package com.demo.rest.models.owner.dto.functions;

import com.demo.rest.models.owner.dto.GetOwnersResponse;
import com.demo.rest.models.owner.entity.Owner;

import java.util.List;
import java.util.function.Function;

public class OwnersToResponseFunction implements Function<List<Owner>, GetOwnersResponse> {
    @Override
    public GetOwnersResponse apply(List<Owner> owners) {
        return GetOwnersResponse.builder()
                .owners(owners.stream()
                        .map(owner -> GetOwnersResponse.Owner.builder()
                                .id(owner.getId())
                                .login(owner.getLogin())
                                .name(owner.getName())
                                .birthDate(owner.getBirthDate())
                                .salary(owner.getSalary())
                                .build())
                        .toList())
                .build();
    }
}
