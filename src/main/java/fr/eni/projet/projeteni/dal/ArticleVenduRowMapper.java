package fr.eni.projet.projeteni.dal;

import fr.eni.projet.projeteni.bll.CategoriService;
import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.ArticleVendu;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {

    private UtilisateurService utilisateurService;
    private CategoriService categoriService;

    public ArticleVenduRowMapper(UtilisateurService utilisateurService, CategoriService categoriService) {
        this.utilisateurService = utilisateurService;
        this.categoriService = categoriService;
    }

    @Override
    public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
        ArticleVendu articleVendu = new ArticleVendu();
        int refAcheteur = (rs.getInt("no_utilisateur"));
        int refCategorie = (rs.getInt("no_categorie"));
        articleVendu.setNoArticle(rs.getInt("no_article"));
        articleVendu.setDescription(rs.getString("description"));
        articleVendu.setDateDebutEncheres(rs.getTimestamp("date_debut_enchere").toLocalDateTime().toLocalDate());
        articleVendu.setDateFinEncheres(rs.getTimestamp("date_fin_enchere").toLocalDateTime().toLocalDate());
        articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
        articleVendu.setPrixVente(rs.getInt("prix_vente"));
        articleVendu.setAcheteur(utilisateurService.getUtilisateur(refAcheteur));
        articleVendu.setCategorie(categoriService.getNoCategorie(refCategorie));
        return articleVendu;
    }
}
