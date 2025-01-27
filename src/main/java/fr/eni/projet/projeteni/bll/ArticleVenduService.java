package fr.eni.projet.projeteni.bll;

import fr.eni.projet.projeteni.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {
    List<ArticleVendu> getAllArticleVendu();

    ArticleVendu getArticleVenduById(int id);

    void createArticleVendu(ArticleVendu articleVendu);

    void updateArticleVendu(ArticleVendu articleVendu);

    void deleteArticleVendu(int id);

    void deleteArticleVendu(ArticleVendu articleVendu);
}
