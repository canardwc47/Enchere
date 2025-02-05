package fr.eni.projet.projeteni.dal;

import fr.eni.projet.projeteni.bo.ArticleVendu;

import java.util.List;

public interface DaoArticleVendu {
    List<ArticleVendu> read();

    ArticleVendu read(int id);

    int create(ArticleVendu articleVendu);

    void update(ArticleVendu articleVendu);

    void delete(ArticleVendu articleVendu);

    void delete(int id);

    List<ArticleVendu> findByCategory(Long categoryId);

    List<ArticleVendu> findByCategoryAndName(Long categoryId, String nomArticle);

    List<ArticleVendu> findByName(String nomArticle);
}
