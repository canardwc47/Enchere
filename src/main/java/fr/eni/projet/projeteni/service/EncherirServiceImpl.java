package fr.eni.projet.projeteni.service;

import fr.eni.projet.projeteni.bll.ArticleVenduService;
import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.ArticleVendu;
import fr.eni.projet.projeteni.bo.Utilisateur;

import java.time.LocalDate;

public class EncherirServiceImpl implements EncherirService {

    private ArticleVenduService articleVenduService;
    private UtilisateurService utilisateurService;

    public EncherirServiceImpl(ArticleVenduService articleVenduService) {
        this.articleVenduService = articleVenduService;
    }

    public EncherirServiceImpl(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public void encherir(int valeurEnchere, int id, Utilisateur utilisateur) {
    ArticleVendu articleVendu = articleVenduService.getArticleVenduById(id);
    if (articleVendu.getVendeur().getNoUtilisateur() == utilisateur.getNoUtilisateur()) {
        throw new IllegalArgumentException("Vous ne pouvez pas surenchérir sur votre propre enchère");
    }
    if (articleVendu.getDateDebutEncheres().isBefore(LocalDate.now())) {
        throw new IllegalArgumentException("L'enchère n'a pas encore commencé.");
    }
    if (articleVendu.getDateFinEncheres().isAfter(LocalDate.now())) {
        throw new IllegalArgumentException("L'enchère est terminée.");
    }

    if (utilisateur.getCredit() < valeurEnchere) {
        throw new IllegalArgumentException("Vous n'avez pas assez de crédit");

    }

    int prixActuel = articleVendu.getMiseAPrix();
    if (prixActuel < valeurEnchere) {
        throw new IllegalArgumentException("L'enchère doit etre superieur au prix actuel");

    }

        articleVendu.setPrixVente(valeurEnchere);
        articleVendu.setAcheteur(utilisateur);
        articleVenduService.updateArticleVendu(articleVendu);



        utilisateur.setCredit(utilisateur.getCredit() - valeurEnchere);
        utilisateurService.updateUtilisateur(utilisateur);
    }


}
