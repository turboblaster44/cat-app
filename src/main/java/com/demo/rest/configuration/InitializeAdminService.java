package com.demo.rest.configuration;


import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.entity.OwnerRoles;
import com.demo.rest.models.owner.repository.api.OwnerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@NoArgsConstructor(force = true)
public class InitializeAdminService {


    private final OwnerRepository ownerRepository;

    private final Pbkdf2PasswordHash passwordHash;


    @Inject
    public InitializeAdminService(
            OwnerRepository ownerRepository,
            @SuppressWarnings("CdiInjectionPointsInspection") Pbkdf2PasswordHash passwordHash
    ) {
        this.ownerRepository = ownerRepository;
        this.passwordHash = passwordHash;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @PostConstruct
    @SneakyThrows
    private void init() {
        if (ownerRepository.findByLogin("admin-service").isEmpty()) {
            Owner admin = Owner.builder()
                    .id(UUID.fromString("004b3156-72d3-4af1-a5b8-05a4d9dea92e"))
                    .name("admin-service")
                    .birthDate(LocalDate.of(1990, 10, 21))
                    .salary(1.0f)
                    .login("admin-service")
                    .password(passwordHash.generate("adminadmin".toCharArray()))
                    .roles(List.of(OwnerRoles.ADMIN, OwnerRoles.OWNER))
                    .build();
            ownerRepository.create(admin);
        }
        else{
            System.out.println("admin already exists");
        }
    }

}