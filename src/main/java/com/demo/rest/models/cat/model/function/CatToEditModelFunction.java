package com.demo.rest.models.cat.model.function;

import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.model.CatEditModel;
import com.demo.rest.models.owner.model.function.OwnerToModelFunction;

import java.io.Serializable;
import java.util.function.Function;

public class CatToEditModelFunction implements Function<Cat, CatEditModel>, Serializable {

    private final OwnerToModelFunction ownerToModelFunction;

    public CatToEditModelFunction(OwnerToModelFunction ownerToModelFunction) {
        this.ownerToModelFunction = ownerToModelFunction;
    }
    @Override
    public CatEditModel apply(Cat entity) {
        return CatEditModel.builder()
                .name(entity.getName())
                .color(entity.getColor())
                .weight(entity.getWeight())
                .owner(ownerToModelFunction.apply(entity.getOwner()))
                .version(entity.getVersion())
                .build();
    }
}
