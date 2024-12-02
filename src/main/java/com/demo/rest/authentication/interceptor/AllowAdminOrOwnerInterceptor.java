package com.demo.rest.authentication.interceptor;

import com.demo.rest.authentication.exception.NoPrincipalException;
import com.demo.rest.authentication.exception.NoRolesException;
import com.demo.rest.authentication.interceptor.biding.AllowAdminOrOwner;
import com.demo.rest.models.cat.entity.Cat;
import com.demo.rest.models.cat.service.CatService;
import com.demo.rest.models.owner.entity.OwnerRoles;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.security.enterprise.SecurityContext;

import java.util.Optional;
import java.util.UUID;


/**
 * Binding for interceptor allowing only admin or user owning the weapon.
 */
@Interceptor
@AllowAdminOrOwner
@Priority(10)
public class AllowAdminOrOwnerInterceptor {


    private final SecurityContext securityContext;


    private final CatService catService;


    @Inject
    public AllowAdminOrOwnerInterceptor(SecurityContext securityContext, CatService catService) {
        this.securityContext = securityContext;
        this.catService = catService;
    }

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        if (securityContext.getCallerPrincipal() == null) {
            throw new NoPrincipalException();
        }
        if (authorized(context)) {
            return context.proceed();
        }
        throw new NoRolesException();
    }

    /**
     * @param context invocation context
     * @return true if caller principal is authorized to edit weapon represented by first method parameter
     */
    private boolean authorized(InvocationContext context) {
        if (securityContext.isCallerInRole(OwnerRoles.ADMIN)) {
            return true;
        } else if (securityContext.isCallerInRole(OwnerRoles.OWNER)) {
            Object provided = context.getParameters()[0];
            Optional<Cat> cat;
            if (provided instanceof Cat) {
                cat = catService.find(((Cat) provided).getId());
            } else if (provided instanceof UUID) {
                cat = catService.find((UUID) provided);
            } else {
                throw new IllegalStateException("No weapon of UUID as first method parameter.");
            }

            return cat.isPresent()
                    && cat.get().getOwner().getLogin().equals(securityContext.getCallerPrincipal().getName());
        }
        return false;
    }

}

