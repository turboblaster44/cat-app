package com.demo.rest.models.breed.view;

import com.demo.rest.models.breed.model.BreedsModel;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.utils.ModelFunctionFactory;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class BreedList {

    private BreedService service;

    private BreedsModel breeds;

    private final ModelFunctionFactory factory;

    @Inject
    public BreedList( ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(BreedService service) {
        this.service = service;
    }

    public BreedsModel getBreeds() {
        if (breeds == null) {
            breeds = factory.breedsToModel().apply(service.findAll());
        }
        return breeds;
    }

    public String deleteAction(BreedsModel.Breed breed) {
        service.deleteById(breed.getId());
        return "breed_list?faces-redirect=true";
    }
}