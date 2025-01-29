package fr.eni.projet.projeteni.converters;

import fr.eni.projet.projeteni.bll.CategoriService;
import fr.eni.projet.projeteni.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategorieConverter implements Converter<String, Categorie> {

    @Autowired
    private CategoriService categoriService;

    @Override
    public Categorie convert(String source) {
        return categoriService.getNoCategorie(Integer.parseInt(source));
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super Categorie, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
