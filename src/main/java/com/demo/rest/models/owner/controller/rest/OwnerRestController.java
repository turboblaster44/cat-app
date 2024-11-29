package com.demo.rest.models.owner.controller.rest;

import com.demo.rest.models.breed.controller.api.BreedController;
import com.demo.rest.models.breed.dto.GetBreedResponse;
import com.demo.rest.models.breed.dto.GetBreedsResponse;
import com.demo.rest.models.breed.dto.PutBreedRequest;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.models.owner.controller.api.OwnerController;
import com.demo.rest.models.owner.dto.GetOwnerResponse;
import com.demo.rest.models.owner.dto.GetOwnersResponse;
import com.demo.rest.models.owner.dto.PutOwnerRequest;
import com.demo.rest.models.owner.service.OwnerService;
import com.demo.rest.utils.DtoFunctionFactory;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.UUID;

@Path("")
public class OwnerRestController implements OwnerController {

    private final OwnerService service;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;
    private HttpServletResponse response;


    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }

    @Inject
    public OwnerRestController(OwnerService service, DtoFunctionFactory factory, UriInfo uriInfo) {
        this.service = service;
        this.factory = factory;
        this.uriInfo = uriInfo;
    }


    @Override
    public GetOwnersResponse getOwners() {
        try{
        return factory.ownersToResponse().apply(service.findAll());
        }catch (EJBAccessException ex){
            throw new ForbiddenException(ex);
        }
    }

    @Override
    public GetOwnerResponse getOwner(UUID id) {
        return service.find(id)
                .map(factory.ownerToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putOwner(UUID id, PutOwnerRequest request) {
        try {
            service.create(factory.requestToOwner().apply(id, request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(OwnerController.class, "getOwner")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void deleteOwner(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(entity),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
