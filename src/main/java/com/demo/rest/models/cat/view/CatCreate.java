package com.demo.rest.models.cat.view;


import com.demo.rest.models.breed.model.BreedModel;
import com.demo.rest.models.breed.service.BreedService;
import com.demo.rest.models.cat.entity.CatColor;
import com.demo.rest.models.cat.model.CatCreateModel;
import com.demo.rest.models.cat.service.CatService;
import com.demo.rest.utils.ModelFunctionFactory;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ConversationScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class CatCreate implements Serializable {


    private final CatService catService;

    private final BreedService breedService;

    private final ModelFunctionFactory factory;


    public List<CatColor> getCatColors() {
        System.out.println("Getting Cat Colors: " + Arrays.asList(CatColor.values()));
        return Arrays.asList(CatColor.values());
    }

    @Getter
    private CatCreateModel cat;

    @Getter
    private List<BreedModel> breeds;

    /**
     * Injected conversation.
     */
    private final Conversation conversation;


    @Inject
    public CatCreate(
            CatService catService,
            BreedService breedService,
            ModelFunctionFactory factory,
            Conversation conversation
    ) {
        this.catService = catService;
        this.factory = factory;
        this.breedService = breedService;
        this.conversation = conversation;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view. @PostConstruct method is called after h:form header is already
     * rendered. Conversation should be started in f:metadata/f:event.
     */
    public void init() {
        if (conversation.isTransient()) {
            breeds = breedService.findAll().stream()
                    .map(factory.breedToModel())
                    .collect(Collectors.toList());
            System.out.println(breeds);
            cat = CatCreateModel.builder()
                    .build();

            conversation.begin();
        }
    }

    public String cancelAction() {
        conversation.end();
        return "/breed/breed_list.xhtml?faces-redirect=true";
    }

    public String saveAction() {
        catService.create(factory.modelToCat().apply(cat));
        conversation.end();
        return "/breed/breed_list.xhtml?faces-redirect=true";
    }


}

