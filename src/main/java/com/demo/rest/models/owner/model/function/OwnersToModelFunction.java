package com.demo.rest.models.owner.model.function;


import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.model.OwnersModel;

import java.util.List;
import java.util.function.Function;

public class OwnersToModelFunction implements Function<List<Owner>, OwnersModel> {
    @Override
    public OwnersModel apply(List<Owner> entity) {
        return OwnersModel.builder()
                .owners(entity.stream()
                        .map(owner -> OwnersModel.Owner.builder()
                                .id(owner.getId())
                                .login(owner.getLogin())
                                .build())
                        .toList())
                .build();
    }
}
