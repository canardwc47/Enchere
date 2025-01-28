package fr.eni.projet.projeteni.controller;


import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConnexionUserController {

    private UtilisateurService utilisateurService;


    public ConnexionUserController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/view-connexion")
    public String login(Model model) {
        model.addAttribute("login", new Utilisateur());
        return "view-connexion";
    }


    @PostMapping("/view-connexion")
    public String login(@ModelAttribute Utilisateur utilisateur, Model model) {
        return "redirect:/view-connexion";
    }
}
