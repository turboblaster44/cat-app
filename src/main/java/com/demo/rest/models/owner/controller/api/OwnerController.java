package com.demo.rest.models.owner.controller.api;

import com.demo.rest.models.owner.dto.GetOwnerResponse;
import com.demo.rest.models.owner.dto.GetOwnersResponse;
import com.demo.rest.models.owner.dto.PutOwnerRequest;

import java.util.UUID;

public interface OwnerController {
    GetOwnersResponse getOwners();

    GetOwnerResponse getOwner(UUID id);

    void deleteOwner(UUID id);

    void putOwner(PutOwnerRequest putOwnerRequest);
}
