package com.demo.rest.utils;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Locale;

@Named("localeSwitcher")
@SessionScoped
public class LocaleSwitcher implements Serializable {
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    public Locale getLocale() {
        return locale;
    }
    public String getLanguage() {
        return locale.getLanguage();
    }
    public void setLanguage(String language) {
        // Ustawienie nowej wartości locale na podstawie języka
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
    public void changeLocale(String language) {
        setLanguage(language); // Wykorzystanie istniejącego setter-a
    }
}
