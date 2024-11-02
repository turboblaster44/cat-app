package com.demo.rest.datastore;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.utils.CloningUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class Datastore {

    private final CloningUtil cloningUtil;
    private final Path imageDir;
    private Set<Owner> owners = new HashSet<>();
    private Set<Breed> breeds = new HashSet<>();
    private Set<Cat> cats = new HashSet<>();

    @Inject
    public Datastore(CloningUtil cloningUtility) throws URISyntaxException {
        this.cloningUtil = cloningUtility;
        System.out.println("imagedir");
        System.out.println(Paths.get(getClass().getClassLoader().getResource("imageDir").toURI()));
        this.imageDir = Paths.get(getClass().getClassLoader().getResource("imageDir").toURI());
    }

    public synchronized List<Owner> findAllOwners() {
        return owners.stream()
                .map(cloningUtil::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Owner> findOwnerByLogin(String login) {
        return owners.stream()
                .filter(owner -> owner.getLogin().equals(login))
                .findFirst()
                .map(cloningUtil::clone);
    }

    public synchronized void createOwner(Owner value) throws IllegalArgumentException {
        if (owners.stream().anyMatch(owner -> owner.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The owner id \"%s\" is not unique".formatted(value.getId()));
        }
        owners.add(cloningUtil.clone(value));
    }

    public synchronized void deleteOwner(UUID id) {
        if (!owners.removeIf(owner -> owner.getId().equals(id))) {
            throw new IllegalArgumentException("No owner found with id: " + id);
        }
    }

    public synchronized void updateOwner(Owner value) throws IllegalArgumentException {
        if (owners.removeIf(owner -> owner.getId().equals(value.getId()))) {
            owners.add(cloningUtil.clone(value));
        } else {
            throw new IllegalArgumentException("The owner with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    public synchronized Path getImagePath(UUID id) {
        return imageDir.resolve(id.toString() + ".png");
    }

    public synchronized void deleteImage(UUID uuid) {
        Path imagePath = getImagePath(uuid);
        try {
            if (Files.exists(imagePath)) {
                Files.delete(imagePath);
            } else {
                throw new NotFoundException();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't delete image with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized void updateImage(UUID uuid, byte[] imageBytes) {
        Path imagePath = getImagePath(uuid);
        try {
            Files.write(imagePath, imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("Can't update image with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized void createImage(UUID uuid, byte[] imageBytes) {
        Path imagePath = getImagePath(uuid);
        try {
            if (Files.exists(imagePath)) {
                throw new IllegalArgumentException("Image with id \"%s\" already exists".formatted(uuid));
            }
            Files.write(imagePath, imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("Can't create image with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized byte[] getImage(UUID uuid) {
        Path imagePath = getImagePath(uuid);
        try {
            if (Files.exists(imagePath)) {
                return Files.readAllBytes(imagePath);
            } else {
                throw new NotFoundException();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't retrieve image with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized List<Breed> findAllBreeds() {
        return breeds.stream()
                .map(cloningUtil::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createBreed(Breed value) throws IllegalArgumentException {
        if (breeds.stream().anyMatch(breed -> breed.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The breed id \"%s\" is not unique".formatted(value.getId()));
        }
        breeds.add(cloningUtil.clone(value));
    }

    public synchronized void deleteBreed(UUID id) {
        if (!breeds.removeIf(breed -> breed.getId().equals(id))) {
            throw new IllegalArgumentException("No breed found with id: " + id);
        }
    }

    public synchronized void updateBreed(Breed value) throws IllegalArgumentException {
        if (breeds.removeIf(breed -> breed.getId().equals(value.getId()))) {
            breeds.add(cloningUtil.clone(value));
        } else {
            throw new IllegalArgumentException("The breed with id \"%s\" does not exist".formatted(value.getId()));
        }
    }


    public synchronized List<Cat> findAllCats() {
        return cats.stream()
                .map(cloningUtil::clone)
                .collect(Collectors.toList());
    }

    public synchronized List<Cat> findCatsByBreedId(UUID id) {
        return cats.stream()
                .filter(cat -> cat.getBreed().getId().equals(id))
                .map(cloningUtil::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createCat(Cat value) throws IllegalArgumentException {
        if (cats.stream().anyMatch(cat -> cat.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The cat id \"%s\" is not unique".formatted(value.getId()));
        }
        Cat entity = cloneWithRelationships(value);
        cats.add(entity);
    }

    public synchronized void deleteCat(UUID id) {
        if (!cats.removeIf(cat -> cat.getId().equals(id))) {
            throw new IllegalArgumentException("No cat found with id: " + id);
        }
    }

    public synchronized void deleteCatsByBreedId(UUID id) {
        boolean removedAny = cats.removeIf(cat -> cat.getBreed().getId().equals(id));
        // If no cats were removed, throw an exception
        if (!removedAny) {
            throw new IllegalArgumentException("No cats found with breed id: " + id);
        }
    }

    public synchronized void updateCat(Cat value) throws IllegalArgumentException {
        Cat entity = cloneWithRelationships(value);
        if (cats.removeIf(cat -> cat.getId().equals(value.getId()))) {
            cats.add(entity);
        } else {
            throw new IllegalArgumentException("The cat with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    private Cat cloneWithRelationships(Cat value) {
        Cat entity = cloningUtil.clone(value);

        if (entity.getOwner() != null) {
            entity.setOwner(owners.stream()
                    .filter(owner -> owner.getId().equals(value.getOwner().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No owner with id \"%s\".".formatted(value.getOwner().getId()))));
        }

        if (entity.getBreed() != null) {
            entity.setBreed(breeds.stream()
                    .filter(breed -> breed.getId().equals(value.getBreed().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No breed with id \"%s\".".formatted(value.getBreed().getId()))));
        }

        return entity;
    }

}
