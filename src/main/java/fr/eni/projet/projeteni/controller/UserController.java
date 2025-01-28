package fr.eni.projet.projeteni.controller;

import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/encheres")
public class UserController {
    // Injection du utilisateurService
    private Utilisateur utilisateur ;
    private UtilisateurService utilisateurService;

    public UserController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    @GetMapping("/profil")
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

    @PostMapping("/profil")
    public String mettreAJourProfil(@ModelAttribute Utilisateur utilisateur, Model model) {
        // Récupère l'utilisateur existant
        model.addAttribute("utilisateur", new Utilisateur());
        utilisateur.setMotDePasse(utilisateurService.getUtilisateur(utilisateur.getEmail()).getMotDePasse());

        System.out.println(utilisateur);

        // Sauvegarde les modifications
        utilisateurService.updateUtilisateur(utilisateur);

        return "redirect:/encheres/profil?email="+ utilisateur.getEmail() ; // Redirige vers le profil mis à jour
    }
}
