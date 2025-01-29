package fr.eni.projet.projeteni.controller;


import fr.eni.projet.projeteni.bll.ArticleVenduService;
import fr.eni.projet.projeteni.bll.EnchereService;
import fr.eni.projet.projeteni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("activeUser")
@RequestMapping("/encheres")
public class EncheresController {

    private final ArticleVenduService articleVenduService;
    private final EnchereService enchereService;

    public EncheresController(ArticleVenduService articleVenduService, EnchereService enchereService) {
        this.articleVenduService = articleVenduService;
        this.enchereService = enchereService;
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
}
