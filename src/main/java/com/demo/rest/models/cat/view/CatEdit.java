package com.demo.rest.models.cat.view;

import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.entity.CatColor;
import com.demo.rest.models.cat.model.CatEditModel;
import com.demo.rest.models.cat.service.CatService;
import com.demo.rest.utils.ModelFunctionFactory;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class CatEdit implements Serializable {


    private final CatService service;

    private final ModelFunctionFactory factory;


    @Setter
    @Getter
    private UUID id;


    @Getter
    private CatEditModel cat;

    public List<CatColor> getCatColors() {
        System.out.println("Getting Cat Colors: " + Arrays.asList(CatColor.values()));
        return Arrays.asList(CatColor.values());
    }

    @Inject
    public CatEdit(CatService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Cat> cat = service.find(id);
        if (cat.isPresent()) {
            this.cat = factory.catToEditModel().apply(cat.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "cat not found");
        }
    }

    public String saveAction() {
        service.update(factory.updateCat().apply(service.find(id).orElseThrow(), cat));
        System.out.println("In save action");
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

}
