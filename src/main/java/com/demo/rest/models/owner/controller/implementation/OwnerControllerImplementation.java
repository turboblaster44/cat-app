package com.demo.rest.models.owner.controller.implementation;

import com.demo.rest.controller.servlet.exception.HttpRequestException;
import com.demo.rest.controller.servlet.exception.NotFoundException;
import com.demo.rest.models.owner.controller.api.OwnerController;
import com.demo.rest.models.owner.dto.GetOwnerResponse;
import com.demo.rest.models.owner.dto.GetOwnersResponse;
import com.demo.rest.models.owner.dto.PutOwnerRequest;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.service.OwnerService;
import com.demo.rest.utils.DtoFunctionFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@RequestScoped
public class OwnerControllerImplementation implements OwnerController {
    private final OwnerService service;
    private final DtoFunctionFactory factory;

    @Inject
    public OwnerControllerImplementation(OwnerService ownerService, DtoFunctionFactory factory) {
        this.service = ownerService;
        this.factory = factory;
    }

    @Override
    public GetOwnersResponse getOwners() {
        return factory.ownersToResponse().apply(service.findAll());
    }

    @Override
    public GetOwnerResponse getOwner(UUID id) {
        return service.find(id)
                .map(factory.ownerToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteOwner(UUID id) {
        service.find(id).ifPresentOrElse(
                owner -> {
                    service.delete(owner);
                },
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void putOwner(PutOwnerRequest putOwnerRequest) {
        Owner owner = factory.requestToOwner().apply(putOwnerRequest);
        System.out.println(owner);
        if (service.find(owner.getId()).isPresent()) {
            service.update(owner);
        } else {

            service.create(owner);
        }

    }
}
