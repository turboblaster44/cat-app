package com.demo.rest.utils;

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
}
