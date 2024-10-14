package com.demo.rest.models.owner.dto.functions;

import com.demo.rest.models.owner.dto.PutOwnerRequest;
import com.demo.rest.models.owner.entity.Owner;

import java.util.function.Function;

public class RequestToOwnerFunction implements Function<PutOwnerRequest, Owner> {
    @Override
    public Owner apply(PutOwnerRequest request) {
        return Owner.builder()
                .id(request.getId())
                .login(request.getLogin())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .salary(request.getSalary())
                .build();
    }
}
