package fr.eni.projet.projeteni.dal;

import fr.eni.projet.projeteni.bll.ArticleVenduService;
import fr.eni.projet.projeteni.bll.CategorieService;
import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.*;
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
        enchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime().toLocalDate());
        enchere.setMontant_enchere(rs.getInt("montant_enchere"));



        Utilisateur user = new Utilisateur();
        user.setPseudo(rs.getString("pseudo"));
        user.setNom(rs.getString("nom"));
        user.setPrenom(rs.getString("prenom"));
        user.setEmail(rs.getString("email"));
        user.setTelephone(rs.getString("telephone"));
        user.setRue(rs.getString("utilisateur_rue"));
        user.setCodePostal(rs.getInt("utilisateur_code_postal"));
        user.setVille(rs.getString("utilisateur_ville"));

        Categorie categorie = new Categorie();
        categorie.setLibelle(rs.getString("categorie_libelle"));

        Retrait retrait = new Retrait();
        retrait.setRue(rs.getString("retrait_rue"));
        retrait.setVille(rs.getString("retrait_ville"));
        retrait.setCode_postal(rs.getInt("retrait_code_postal"));

        ArticleVendu article = new ArticleVendu();
        article.setNomArticle(rs.getString("nom_article"));
        article.setDescription(rs.getString("description"));
        article.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime().toLocalDate());
        article.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime().toLocalDate());
        article.setMiseAPrix(rs.getInt("prix_initial"));
        article.setPrixVente(rs.getInt("prix_vente"));
        article.setCategorie(categorie);
        article.setLieuRetrait(retrait);
        article.setVendeur(user);


        enchere.setArticleVendu(article);

        return enchere;
    }
}
