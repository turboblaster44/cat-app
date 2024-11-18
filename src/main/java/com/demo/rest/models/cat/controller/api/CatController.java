package com.demo.rest.models.breed.controller.api;


import com.demo.rest.models.breed.dto.GetBreedResponse;
import com.demo.rest.models.breed.dto.GetBreedsResponse;
import com.demo.rest.models.breed.dto.PutBreedRequest;
import com.demo.rest.models.cat.dto.GetCatResponse;
import com.demo.rest.models.cat.dto.GetCatsResponse;
import com.demo.rest.models.cat.dto.PutCatRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface CatController {

    /**
     * @return all professions representation
     */
    @GET
    @Path("/cats")
    @Produces(MediaType.APPLICATION_JSON)
    GetCatsResponse getCats();

    /**
     * @param id profession's id
     * @return profession
     */
    @GET
    @Path("/cats/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetCatResponse getCat(@PathParam("id") UUID id);

    @GET
    @Path("/breeds/{id}/cats")
    @Produces(MediaType.APPLICATION_JSON)
    GetCatsResponse getCatsByBreed(@PathParam("id") UUID id);



    @PUT
    @Path("/breeds/{breedId}/cats/{catId}")
    @Consumes({MediaType.APPLICATION_JSON})
    void putCat(@PathParam("breedId") UUID breedId,@PathParam("catId") UUID catId, PutCatRequest request);

    /**
     * @param id profession's id
     */
    @DELETE
    @Path("/cats/{id}")
    void deleteCat(@PathParam("id") UUID id);

}
