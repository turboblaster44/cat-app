package com.demo.rest.utils;

import com.demo.rest.models.breed.model.function.BreedToModelFunction;
import com.demo.rest.models.breed.model.function.BreedsToModelFunction;
import com.demo.rest.models.cat.model.function.*;
import com.demo.rest.models.owner.model.function.OwnerToModelFunction;
import com.demo.rest.models.owner.model.function.OwnersToModelFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModelFunctionFactory {

    public CatToModelFunction catToModelFunction() {
        return new CatToModelFunction();
    }

    public CatsToModelFunction catsToModel() {
        return new CatsToModelFunction();
    }

    public BreedToModelFunction breedToModel() {
        return new BreedToModelFunction();
    }

    public BreedsToModelFunction breedsToModel() {
        return new BreedsToModelFunction();
    }

    public ModelToCatFunction modelToCat() {
        return new ModelToCatFunction();
    }

    public CatToEditModelFunction catToEditModel() {
        return new CatToEditModelFunction(ownerToModel());
    }
    public UpdateCatWithModelFunction updateCat() {return new UpdateCatWithModelFunction();}

    public OwnerToModelFunction ownerToModel() {
        return new OwnerToModelFunction();
    }

    public OwnersToModelFunction ownersToModel() {
        return new OwnersToModelFunction();
    }
}