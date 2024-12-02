package com.demo.rest.models.owner.view;

import com.demo.rest.models.owner.model.OwnersModel;
import com.demo.rest.models.owner.service.OwnerService;
import com.demo.rest.utils.ModelFunctionFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class OwnerList {
    private final OwnerService service;


    private OwnersModel owners;

    private final ModelFunctionFactory factory;


    @Inject
    public OwnerList(OwnerService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public OwnersModel getOwners() {
        if (owners == null) {
            owners = factory.ownersToModel().apply(service.findAll());
        }
        return owners;
    }

    public String deleteAction(OwnersModel.Owner owner) {
        service.delete(owner.getId());
        return "owners_list?faces-redirect=true";
    }

}