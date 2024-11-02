package com.demo.rest.utils;

import com.demo.rest.models.breed.dto.function.BreedToResponseFunction;
import com.demo.rest.models.breed.dto.function.BreedsToResponseFunction;
import com.demo.rest.models.breed.dto.function.RequestToBreedFunction;
import com.demo.rest.models.cat.dto.function.CatToResponseFunction;
import com.demo.rest.models.cat.dto.function.CatsToResponseFunction;
import com.demo.rest.models.cat.dto.function.RequestToCatFunction;
import com.demo.rest.models.owner.dto.functions.OwnerToResponseFunction;
import com.demo.rest.models.owner.dto.functions.OwnersToResponseFunction;
import com.demo.rest.models.owner.dto.functions.RequestToOwnerFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DtoFunctionFactory {
    public OwnersToResponseFunction ownersToResponse() {
        return new OwnersToResponseFunction();
    }

    public OwnerToResponseFunction ownerToResponse() {
        return new OwnerToResponseFunction();
    }

    public RequestToOwnerFunction requestToOwner() {
        return new RequestToOwnerFunction();
    }

    public BreedsToResponseFunction breedsToResponse() {
        return new BreedsToResponseFunction();
    }

    public BreedToResponseFunction breedToResponse() {
        return new BreedToResponseFunction();
    }

    public RequestToBreedFunction requestToBreed() {
        return new RequestToBreedFunction();
    }

    public CatsToResponseFunction catsToResponse() {
        return new CatsToResponseFunction();
    }

    public CatToResponseFunction catToResponse() {
        return new CatToResponseFunction();
    }

    public RequestToCatFunction requestToCat() {
        return new RequestToCatFunction();
    }


}
