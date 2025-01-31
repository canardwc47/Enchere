package fr.eni.projet.projeteni.service;

import fr.eni.projet.projeteni.bll.ArticleVenduService;
import fr.eni.projet.projeteni.bo.ArticleVendu;
import fr.eni.projet.projeteni.bo.Utilisateur;

import java.time.LocalDate;

public class FinEnchereServiceImpl {

    private ArticleVenduService articleVenduService;

    public FinEnchereServiceImpl(ArticleVenduService articleVenduService) {
        this.articleVenduService = articleVenduService;

    }

    public void finEnchere(int prixVente, int id, Utilisateur utilisateur) {
        ArticleVendu articleVendu = articleVenduService.getArticleVenduById(id);

        if (articleVendu.getDateFinEncheres().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("L'enchère n'est pas terminée.");
        }


    }
}