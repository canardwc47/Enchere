package fr.eni.projet.projeteni.controller;

import fr.eni.projet.projeteni.bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VenterController {
    @GetMapping("/vente")
    public String vente(Model model) {
        model.addAttribute("venter", new ArticleVendu());
        return "/view-creation-vente";
    }


    @GetMapping("/acceul")
    public String acceul() {
        return "/view-accueil";
    }

}
