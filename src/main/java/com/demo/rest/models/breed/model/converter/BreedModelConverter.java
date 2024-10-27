package com.demo.rest.models.breed.model.converter;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.model.BreedModel;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.utils.ModelFunctionFactory;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

/**
 * Faces converter  The managed attribute in {@link @FacesConverter} allows the converter to
 * be the CDI bean. In previous version of JSF converters were always created inside JSF lifecycle and where not managed
 * by container that is injection was not possible. As this bean is not annotated with scope the beans.xml descriptor
 * must be present.
 */
@FacesConverter(forClass = BreedModel.class, managed = true)
public class BreedModelConverter implements Converter<BreedModel> {

    private final BreedService service;

    private final ModelFunctionFactory factory;


    @Inject
    public BreedModelConverter(BreedService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public BreedModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Breed> breed = service.find(UUID.fromString(value));
        return breed.map(factory.breedToModel()).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, BreedModel value) {
        return value == null ? "" : value.getId().toString();
    }

}