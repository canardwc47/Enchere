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
@SessionAttributes("activeUser")
@RequestMapping("/encheres")
public class UserController {

    private UtilisateurService utilisateurService;

    public UserController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    @ModelAttribute("activeUser")
    public Utilisateur getActiveUser() {
        return new Utilisateur();
    }

//WORKS
    @GetMapping("/connexion")
    public String login(Model model) {
        model.addAttribute("login", new Utilisateur());
        return "view-connexion";
    }

//DOSENT WORK
    @PostMapping("/connexion")
    public String login(@RequestParam(name = "email") String email,@RequestParam(name = "password") String mdp,@ModelAttribute("activeUser") Utilisateur activeUer, Model model) {
        if (utilisateurService.getUtilisateur(email) != null && utilisateurService.getUtilisateur(email).getMotDePasse().equals(mdp)) {
            Utilisateur user = utilisateurService.getUtilisateur(email);
            if (user != null) {
                activeUer.setEmail(email);
                activeUer.setPrenom((user.getPrenom()));
                activeUer.setNom(user.getNom());
                activeUer.setTelephone(user.getTelephone());
                activeUer.setRue(user.getRue());
                activeUer.setCodePostal(user.getCodePostal());
                activeUer.setVille(user.getVille());
                activeUer.setPseudo(user.getPseudo());
                activeUer.setMotDePasse(mdp);
            }else {
                activeUer.setEmail(null);
                activeUer.setPrenom(null);
                activeUer.setNom(null);
                activeUer.setTelephone(null);
                activeUer.setRue(null);
                activeUer.setCodePostal(10000);
                activeUer.setVille(null);
                activeUer.setPseudo(null);
            }
        }
        return "redirect:/encheres/profil";
    }

//WORKS
    @GetMapping("/inscription")
    public String creerunChat(Model model) {
        model.addAttribute("creationCompte", new Utilisateur());
        return "view-creation-compte";
    }


//WORKS (STILL NEEDS PWD ENCRYPTION)
    @PostMapping("/inscription")
    public String newChat(@ModelAttribute("activeUser") Utilisateur activeUer,@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.addUtilisateur(utilisateur);
        activeUer = utilisateur;

        return "redirect:/encheres";
    }


//WORKS
    @GetMapping("/profil")
    public String profil(@ModelAttribute("activeUser") Utilisateur userActif, Model model) {

        if (userActif == null) {
            // Rediriger ou afficher un message si l'utilisateur n'existe pas
            System.out.println("error " + "Utilisateur déconnecté");
            return "redirect:/"; // Assure-toi d'avoir une page "error.html"
        }
        return "view-profil";
    }


//WORKS
    @PostMapping("/profil")
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
