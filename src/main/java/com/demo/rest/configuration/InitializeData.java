package com.demo.rest.configuration;

import com.demo.rest.models.breed.entity.Breed;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.entity.CatColor;
import com.demo.rest.models.cat.service.CatService;
import com.demo.rest.models.image.service.ImageService;
import com.demo.rest.models.owner.entity.Owner;
import com.demo.rest.models.owner.service.OwnerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextListener;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class InitializeData implements ServletContextListener {
    private final OwnerService ownerService;
    private final BreedService breedService;
    private final CatService catService;
    private final ImageService imageService;
    private final static Path imageDir = Paths.get("imageDir");

    @Inject
    public InitializeData(OwnerService ownerService, ImageService imageService,
                          BreedService breedService, CatService catService
    ) {
        this.ownerService = ownerService;
        this.imageService = imageService;
        this.breedService = breedService;
        this.catService = catService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    private void initImageDir(Path imageDir) {
        try {
            Files.createDirectories(imageDir);
            System.out.println("directory created: " + imageDir.toAbsolutePath());
            //clear directory
            Files.walk(imageDir)
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            System.err.println("Could not delete file: " + file);
                        }
                    });

        } catch (IOException e) {
            throw new IllegalStateException("exception when creating picture directory", e);
        }
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @SneakyThrows
    private void init() {
        initImageDir(imageDir);

        Owner albert = Owner.builder()
                .id(UUID.fromString("45a3c22d-77d0-4571-9f4b-4de78b3e5796"))
                .name("albert")
                .login("albert")
                .salary(10000.0f)
                .birthDate(LocalDate.now())
                .build();
        Owner bartek = Owner.builder()
                .id(UUID.fromString("b98f3729-9a89-4ebb-b434-4d2332f5005f"))
                .name("bartek")
                .login("bartek")
                .salary(15000.0f)
                .birthDate(LocalDate.now())
                .build();
        Owner zenek = Owner.builder()
                .id(UUID.fromString("d80ecfee-a46f-4d05-b02c-8e9664b5982f"))
                .name("zenek")
                .login("zenek")
                .salary(100.0f)
                .birthDate(LocalDate.now())
                .build();
        Owner karol = Owner.builder()
                .id(UUID.fromString("2f4d3424-8f9b-4e58-bd0a-764bd776ccde"))
                .name("karol")
                .login("karol")
                .salary(100.0f)
                .birthDate(LocalDate.now())
                .build();


        ownerService.create(albert);
        ownerService.create(bartek);
        ownerService.create(zenek);
        ownerService.create(karol);

        Breed maineCoon = Breed.builder()
                .id(UUID.fromString("86b6b195-901d-4796-870d-b2719362c677"))
                .name("Maine Coon")
                .averageLifespan(12)
                .isHypoallergenic(false)
                .build();

        Breed siamese = Breed.builder()
                .id(UUID.fromString("6f337b34-fc41-48f1-9173-9514935972ba"))
                .name("Siamese")
                .averageLifespan(15)
                .isHypoallergenic(true)
                .build();

        Breed ragdoll = Breed.builder()
                .id(UUID.fromString("b245181e-1f31-4ca5-906f-55ca5a88ca11"))
                .name("Ragdoll")
                .averageLifespan(14)
                .isHypoallergenic(false)
                .build();

        Breed sphynx = Breed.builder()
                .id(UUID.fromString("2c8e3434-392b-4b62-ab05-97ad89e7c674"))
                .name("Sphynx")
                .averageLifespan(13)
                .isHypoallergenic(true)
                .build();

        breedService.create(maineCoon);
        breedService.create(siamese);
        breedService.create(ragdoll);
        breedService.create(sphynx);

        Cat whiskers = Cat.builder()
                .id(UUID.fromString("e07882dd-3cba-4a59-af8e-3098538f0319"))
                .name("Whiskers")
                .color(CatColor.ORANGE)
                .weight(4.5f)
                .breed(maineCoon)
                .owner(albert)
                .build();

        Cat shadow = Cat.builder()
                .id(UUID.fromString("67c165b2-b793-41e7-8969-fd1bd8813e14"))
                .name("Shadow")
                .color(CatColor.BLACK)
                .weight(3.8f)
                .breed(siamese)
                .owner(bartek)
                .build();

        Cat snowball = Cat.builder()
                .id(UUID.fromString("48021e06-102c-4c9d-bf4d-731f481fb59d"))
                .name("Snowball")
                .color(CatColor.WHITE)
                .weight(5.0f)
                .breed(ragdoll)
                .owner(zenek)
                .build();

        Cat cleo = Cat.builder()
                .id(UUID.fromString("0bd7028a-f8fc-40fe-95bf-51d56797ddb8"))
                .name("Cleo")
                .color(CatColor.GREY)
                .weight(3.2f)
                .breed(sphynx)
                .owner(karol)
                .build();

        catService.create(whiskers);
        catService.create(shadow);
        catService.create(snowball);
        catService.create(cleo);

        DisplayData();

    }

    private void DisplayData() {
        System.out.println("==================CATS====================");
        catService.findAll().forEach(cat -> {
            System.out.println();
            System.out.println("Cat: " + cat.getName());
            System.out.println("Color: " + cat.getColor());
            System.out.println("Weight: " + cat.getWeight() + " kg");
            System.out.println("Breed: " + (cat.getBreed() != null ? cat.getBreed().getName() : "No breed information"));
            System.out.println("Owner: " + (cat.getOwner() != null ? cat.getOwner().getName() : "No owner information"));
            System.out.println();
        });
        System.out.println();

        System.out.println("==================BREEDS====================");
        breedService.findAll().forEach(breed -> {
            System.out.println();
            System.out.println("Breed: " + breed.getName());
            System.out.println("Average Lifespan: " + breed.getAverageLifespan() + " years");
            System.out.println("Hypoallergenic: " + (breed.isHypoallergenic() ? "Yes" : "No"));
            // If you want to display the cats of this breed (if you have this relationship set up)
            List<Cat> catsOfBreed = catService.findByBreed(breed);
            if (catsOfBreed != null && !catsOfBreed.isEmpty()) {
                System.out.println("Cats of this breed:");
                catsOfBreed.forEach(cat -> System.out.println("    - " + cat.getName()));
            } else {
                System.out.println("    No cats available for this breed.");
            }
        });
        System.out.println();

        System.out.println("==================OWNERS====================");
        ownerService.findAll().forEach(owner -> {
            System.out.println();
            System.out.println("Owner: " + owner.getName());
            System.out.println("Login: " + owner.getLogin());
            System.out.println("Salary: $" + owner.getSalary());
            System.out.println("Birth Date: " + owner.getBirthDate());
            // Display the cats owned by this owner (if you have this relationship set up)
            List<Cat> catsOfOwner = catService.findByOwner(owner);
            if (catsOfOwner != null && !catsOfOwner.isEmpty()) {
                System.out.println("Cats owned:");
                catsOfOwner.forEach(cat -> System.out.println("    - " + cat.getName()));
            } else {
                System.out.println("    No cats available for this owner.");
            }
        });
        System.out.println();
    }

    private byte[] getImageBytes(UUID id) throws IOException {
        Path resPath = Paths.get("C:\\Users\\micha\\OneDrive\\Pulpit\\GIT REPOS\\cat-app\\src\\main\\resources\\imageDir\\" + id.toString() + ".png");
        return Files.readAllBytes(resPath);
    }
}
