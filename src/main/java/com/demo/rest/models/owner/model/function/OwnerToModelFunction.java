package com.demo.rest.models.owner.model.function;

import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.model.OwnerModel;

import java.io.Serializable;
import java.util.function.Function;

public class OwnerToModelFunction implements Function<Owner, OwnerModel>, Serializable {

    @Override
    public OwnerModel apply(Owner entity) {
        return OwnerModel.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .build();
    }
}