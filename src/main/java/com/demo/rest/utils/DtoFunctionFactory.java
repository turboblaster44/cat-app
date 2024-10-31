package com.demo.rest.utils;

import com.demo.rest.models.breed.dto.funcion.BreedToResponseFunction;
import com.demo.rest.models.breed.dto.funcion.BreedsToResponseFunction;
import com.demo.rest.models.breed.dto.funcion.RequestToBreedFunction;
import com.demo.rest.models.owner.dto.functions.OwnerToResponseFunction;
import com.demo.rest.models.owner.dto.functions.OwnersToResponseFunction;
import com.demo.rest.models.owner.dto.functions.RequestToOwnerFunction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Request;

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


}
