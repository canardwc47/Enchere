package fr.eni.projet.projeteni.controller;


import fr.eni.projet.projeteni.bll.ArticleVenduService;
import fr.eni.projet.projeteni.bll.CategorieService;
import fr.eni.projet.projeteni.bll.EnchereService;
import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.ArticleVendu;
import fr.eni.projet.projeteni.bo.Enchere;
import fr.eni.projet.projeteni.bo.Retrait;
import fr.eni.projet.projeteni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/encheres")
public class EncheresController {

    private ArticleVenduService articleVenduService;
    private EnchereService enchereService;
    private UtilisateurService utilisateurService;
    private CategorieService categorieService;

    public EncheresController(ArticleVenduService articleVenduService, EnchereService enchereService, UtilisateurService utilisateurService, CategorieService categorieService) {
        this.articleVenduService = articleVenduService;
        this.enchereService = enchereService;
        this.utilisateurService = utilisateurService;
        this.categorieService = categorieService;
    }

    //DOESNT WORK "MISSING VIEW CODE"
    @GetMapping("/detail")
    public String afficherEnchereDetail(@RequestParam(name = "id") int id , Model model) {
        model.addAttribute("enchere", enchereService.getEnchere(id));
        System.out.println(enchereService.getEnchere(id));
        return "view-detail-vente";
    }

    //OFC IT WORKS
    @GetMapping
    public String encheres(Model model) {
        var articles = articleVenduService.getAllArticleVendu();
        model.addAttribute("articles", articles);
        return "view-encheres";
    }


    //send a form to fill
    @GetMapping("/cree")
    public String creeEnchere(Model model) {
        var article = new ArticleVendu();
        var enchere = new Enchere();
        model.addAttribute("article", article);
        return "view-creation-vente";
    }





    //post the filled form
    @PostMapping("/cree")
    public String creeEnchere(@RequestParam (name = "nom") String nom,
                              @ModelAttribute("activeUser") Utilisateur activeUser,
                              @RequestParam (name = "description") String description,
                              @RequestParam (name = "categorie") String categorie,
                              @RequestParam (name = "prix") int prix,
                              @RequestParam (name = "debut") LocalDate debut,
                              @RequestParam (name = "fin") LocalDate fin,
                              @RequestParam (name = "rue") String rue,
                              @RequestParam (name = "code_postal") int code_postal,
                              @RequestParam (name = "ville") String ville) {

        Utilisateur utilisateur = utilisateurService.getUtilisateur(activeUser.getEmail());

        Retrait retrait = new Retrait();
        retrait.setRue(rue);
        retrait.setCode_postal(code_postal);
        retrait.setVille(ville);

        ArticleVendu articleVendu = new ArticleVendu();
        articleVendu.setVendeur(utilisateur);
        articleVendu.setNomArticle(nom);
        articleVendu.setDescription(description);
        articleVendu.setCategorie(categorieService.getCategorieByName(categorie));
        articleVendu.setMiseAPrix(prix);
        articleVendu.setDateDebutEncheres(debut);
        articleVendu.setDateFinEncheres(fin);
        articleVendu.setLieuRetrait(retrait);

        Enchere enchere = new Enchere();
        enchere.setDateEnchere(debut);
        enchere.setMontant_enchere(prix);
        enchere.setArticleVendu(articleVendu);

        enchereService.addEnchere(enchere);

    return "redirect:/encheres";
    }



}
