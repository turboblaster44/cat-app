package com.demo.rest.models.cat.view;

import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.model.CatModel;
import com.demo.rest.models.cat.service.CatService;
import com.demo.rest.utils.ModelFunctionFactory;
import jakarta.ejb.EJBAccessException;
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
public class CatView implements Serializable {

    private final CatService catService;
    private final ModelFunctionFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private CatModel cat;


    @Inject
    public CatView(ModelFunctionFactory factory, CatService catService) {
        this.factory = factory;
        this.catService = catService;
    }


    public void init() throws IOException {
        try {
            Optional<Cat> cat = catService.find(id);
            if (cat.isPresent()) {
                this.cat = factory.catToModel().apply(cat.get());
            } else {
                FacesContext.getCurrentInstance().getExternalContext()
                        .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Cat not found");
            }
        } catch (EJBAccessException e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().responseSendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }

}