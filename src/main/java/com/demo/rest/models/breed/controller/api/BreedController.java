package com.demo.rest.models.breed.controller.api;


import com.demo.rest.models.breed.dto.GetBreedResponse;
import com.demo.rest.models.breed.dto.GetBreedsResponse;
import com.demo.rest.models.breed.dto.PutBreedRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface BreedController {

    /**
     * @return all professions representation
     */
    @GET
    @Path("/breeds")
    @Produces(MediaType.APPLICATION_JSON)
    GetBreedsResponse getBreeds();

    /**
     * @param id profession's id
     * @return profession
     */
    @GET
    @Path("/breeds/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetBreedResponse getBreed(@PathParam("id") UUID id);


    @PUT
    @Path("/breeds/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    void putBreed(@PathParam("id") UUID id, PutBreedRequest request);

    /**
     * @param id profession's id
     */
    @DELETE
    @Path("/breeds/{id}")
    void deleteBreed(@PathParam("id") UUID id);

}
