package com.demo.rest.models.owner.controller.api;

import com.demo.rest.models.breed.dto.GetBreedResponse;
import com.demo.rest.models.breed.dto.GetBreedsResponse;
import com.demo.rest.models.breed.dto.PutBreedRequest;
import com.demo.rest.models.owner.dto.GetOwnerResponse;
import com.demo.rest.models.owner.dto.GetOwnersResponse;
import com.demo.rest.models.owner.dto.PutOwnerRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface OwnerController {

    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    GetOwnersResponse getOwners();


    @GET
    @Path("/owners/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetOwnerResponse getOwner(@PathParam("id") UUID id);


    @PUT
    @Path("/owners/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    void putOwner(@PathParam("id") UUID id, PutOwnerRequest request);

    /**
     * @param id profession's id
     */
    @DELETE
    @Path("/owners/{id}")
    void deleteOwner(@PathParam("id") UUID id);

}