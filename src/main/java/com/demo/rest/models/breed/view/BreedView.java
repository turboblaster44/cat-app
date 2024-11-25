package com.demo.rest.models.breed.view;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.model.BreedModel;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.models.cat.model.CatsModel;
import com.demo.rest.models.cat.service.CatService;
import com.demo.rest.utils.ModelFunctionFactory;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class BreedView implements Serializable {

    private BreedService breedService;
    private CatService catService;
    private final ModelFunctionFactory factory;


    @Setter
    @Getter
    private UUID id;


    @Getter
    private BreedModel breed;

    @Getter
    private CatsModel cats;


    @Inject
    public BreedView(ModelFunctionFactory factory) {
        this.factory = factory;

    }

    @EJB
    public void setCatService(CatService service) {
        this.catService = service;
    }


    @EJB
    public void setBreedService(BreedService service) {
        this.breedService = service;
    }

    public void init() throws IOException {
        Optional<Breed> breed = breedService.find(id);
        if (breed.isPresent()) {
            this.breed = factory.breedToModel().apply(breed.get());
            this.cats = factory.catsToModel().apply(catService.findByBreedId(breed.get().getId()));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "weaponType not found");
        }
    }

    public String deleteCat(CatsModel.Cat cat) {
        catService.deleteById(cat.getId());
        return "breed_view?faces-redirect=true&id=" + this.id;
    }

}