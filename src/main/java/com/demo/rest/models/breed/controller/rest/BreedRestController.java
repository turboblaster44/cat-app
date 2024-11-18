package com.demo.rest.models.breed.controller.rest;

import com.demo.rest.models.breed.controller.api.BreedController;
import com.demo.rest.models.breed.dto.GetBreedResponse;
import com.demo.rest.models.breed.dto.GetBreedsResponse;
import com.demo.rest.models.breed.dto.PutBreedRequest;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.utils.DtoFunctionFactory;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.UUID;

@Path("")
public class BreedRestController implements BreedController {
    private final BreedService service;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;
    private HttpServletResponse response;


    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }

    @Inject
    public BreedRestController(BreedService service, DtoFunctionFactory factory, UriInfo uriInfo) {
        System.out.println("initing a controller");
        this.service = service;
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @Override
    public GetBreedsResponse getBreeds() {
        return factory.breedsToResponse().apply(service.findAll());
    }


    @Override
    public GetBreedResponse getBreed(UUID id) {
        return service.find(id)
                .map(factory.breedToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putBreed(UUID id, PutBreedRequest request) {
        try {
            service.put(factory.requestToBreed().apply(id, request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(BreedController.class, "getBreed")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void deleteBreed(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(entity),
                () -> {
                    throw new NotFoundException();
                }
        );
    }


}
