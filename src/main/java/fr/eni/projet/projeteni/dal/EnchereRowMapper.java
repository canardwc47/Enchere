package fr.eni.projet.projeteni.dal;

import fr.eni.projet.projeteni.bll.ArticleVenduService;
import fr.eni.projet.projeteni.bll.CategorieService;
import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.Enchere;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EnchereRowMapper implements RowMapper<Enchere> {

    private UtilisateurService utilisateurService;
    private ArticleVenduService articleVenduService;
    private CategorieService categorieService;

    public EnchereRowMapper(UtilisateurService utilisateurService, ArticleVenduService articleVenduService) {
        this.utilisateurService = utilisateurService;
        this.articleVenduService = articleVenduService;
    }

    @Override
    public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enchere enchere = new Enchere();
        int refNoUser = (rs.getInt("no_utilisateur"));
        int refNoArticle = (rs.getInt("no_article"));
        enchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime().toLocalDate());
        enchere.setMontant_enchere(rs.getInt("montant_enchere"));
        enchere.setLastBidder(utilisateurService.getUtilisateur(refNoUser));
        enchere.setArticleVendu(articleVenduService.getArticleVenduById(refNoArticle));

        return enchere;
    }
}
