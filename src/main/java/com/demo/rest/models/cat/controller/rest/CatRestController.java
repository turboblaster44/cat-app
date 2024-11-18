package com.demo.rest.models.cat.controller.rest;

import com.demo.rest.models.breed.controller.api.BreedController;
import com.demo.rest.models.breed.controller.api.CatController;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.models.cat.dto.GetCatResponse;
import com.demo.rest.models.cat.dto.GetCatsResponse;
import com.demo.rest.models.cat.dto.PutCatRequest;
import com.demo.rest.models.cat.service.CatService;
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
public class CatRestController implements CatController {
    private final CatService service;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;
    private HttpServletResponse response;


    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }

    @Inject
    public CatRestController(CatService service, DtoFunctionFactory factory, UriInfo uriInfo) {
        this.service = service;
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @Override
    public GetCatsResponse getCats() {
        return factory.catsToResponse().apply(service.findAll());
    }

    @Override
    public GetCatResponse getCat(UUID id) {
        return service.find(id)
                .map(factory.catToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetCatsResponse getCatsByBreed(UUID id) {
        return factory.catsToResponse().apply(service.findByBreedId(id));
    }

    @Override
    public void putCat(UUID breedId, UUID catId, PutCatRequest request) {
        try {
            service.put(factory.requestToCat().apply(breedId, catId, request));
            System.out.println("after create");
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(CatController.class, "getCat")
                    .build(catId)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void deleteCat(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.deleteById(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
