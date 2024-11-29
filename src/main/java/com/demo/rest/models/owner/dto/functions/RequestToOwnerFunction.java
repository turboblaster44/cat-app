package com.demo.rest.models.owner.dto.functions;

import com.demo.rest.models.breed.dto.PutBreedRequest;
import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.owner.dto.PutOwnerRequest;
import com.demo.rest.models.owner.entity.Owner;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RequestToOwnerFunction implements BiFunction<UUID, PutOwnerRequest, Owner> {
    @Override
    public Owner apply(UUID id, PutOwnerRequest request) {
        return Owner.builder()
                .id(id)
                .login(request.getLogin())
                .password(request.getPassword())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .salary(request.getSalary())
                .build();
    }
}
