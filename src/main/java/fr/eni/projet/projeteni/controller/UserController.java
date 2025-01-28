package fr.eni.projet.projeteni.controller;

import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/encheres")
public class UserController {

    private UtilisateurService utilisateurService;

    public UserController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


//WORKS
    @GetMapping("/connexion")
    public String login(Model model) {
        model.addAttribute("login", new Utilisateur());
        return "view-connexion";
    }

//DOSENT WORK
    @PostMapping("/connexion")
    public String login(@ModelAttribute Utilisateur utilisateur, Model model) {
        return "redirect:/view-connexion";
    }

//WORKS
    @GetMapping("/inscription")
    public String creerunChat(Model model) {
        model.addAttribute("creationCompte", new Utilisateur());
        return "view-creation-compte";
    }


//WORKS (STILL NEEDS PWD ENCRYPTION)
    @PostMapping("/inscription")
    public String newChat(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.addUtilisateur(utilisateur);
        System.out.println(utilisateur);
        return "redirect:/encheres";
    }


//WORKS
    @GetMapping("/modif")
    public String profil(@RequestParam(name = "email") String email, Model model) {
        Utilisateur utilisateur = utilisateurService.getUtilisateur(email);
        if (utilisateur == null) {
            // Rediriger ou afficher un message si l'utilisateur n'existe pas
            model.addAttribute("error", "Utilisateur introuvable pour l'email : " + email);
            return "error"; // Assure-toi d'avoir une page "error.html"
        }
        model.addAttribute("utilisateur", utilisateur);
        return "view-profil";
    }



//WORKS
    @PostMapping("/modif")
    public String mettreAJourProfil(@ModelAttribute Utilisateur utilisateur, Model model) {
        // Récupère l'utilisateur existant
        model.addAttribute("utilisateur", new Utilisateur());
        utilisateur.setMotDePasse(utilisateurService.getUtilisateur(utilisateur.getEmail()).getMotDePasse());

        System.out.println(utilisateur);

        // Sauvegarde les modifications
        utilisateurService.updateUtilisateur(utilisateur);

        return "redirect:/encheres/modif?email="+ utilisateur.getEmail() ; // Redirige vers le profil mis à jour
    }
}
