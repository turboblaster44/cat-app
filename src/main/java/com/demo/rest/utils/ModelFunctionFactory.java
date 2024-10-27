package com.demo.rest.utils;

import com.demo.rest.models.breed.model.function.BreedToModelFunction;
import com.demo.rest.models.breed.model.function.BreedsToModelFunction;
import com.demo.rest.models.cat.model.function.CatToModelFunction;
import com.demo.rest.models.cat.model.function.CatsToModelFunction;
import com.demo.rest.models.cat.model.function.ModelToCatFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModelFunctionFactory {

    public CatToModelFunction catToModelFunction() {return new CatToModelFunction();}
    public CatsToModelFunction catsToModel() {
        return new CatsToModelFunction();
    }
    public BreedToModelFunction breedToModel() {return new BreedToModelFunction();}
    public BreedsToModelFunction breedsToModel() {return new BreedsToModelFunction();}
    public ModelToCatFunction modelToCatFunction() {return new ModelToCatFunction();}
}