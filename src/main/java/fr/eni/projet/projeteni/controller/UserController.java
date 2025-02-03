package fr.eni.projet.projeteni.controller;

import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

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
    public String creerunCompte(Model model) {
        model.addAttribute("creationCompte", new Utilisateur());
        return "view-creation-compte";
    }


//WORKS (STILL NEEDS PWD ENCRYPTION)
    @PostMapping("/inscription")
    public String newCompte(@ModelAttribute("activeUser") Utilisateur activeUer,@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.addUtilisateur(utilisateur);
        activeUer = utilisateur;

        return "redirect:/encheres";
    }





//this shows the profile page
    @GetMapping("/profil")
    public String profil(@ModelAttribute("activeUser") Utilisateur userActif, Model model) {

        if (userActif == null) {
            // Rediriger ou afficher un message si l'utilisateur n'existe pas
            System.out.println("error " + "Utilisateur déconnecté");
            return "redirect:/"; // Assure-toi d'avoir une page "error.html"
        }
        return "view-profil";
    }




@GetMapping("/modif")
public String modif(
        @ModelAttribute("activeUser") Utilisateur userActif,
        @RequestParam(name = "email", required = false) String email,
        Model model) {

    // Determine which email to use (priority: request param > active user)
    String userEmail = (email != null) ? email : (userActif != null ? userActif.getEmail() : null);

    if (userEmail == null) {
        // If no email is available, redirect or show an error
        System.out.println("Erreur: Aucun utilisateur actif ou email fourni");
        return "redirect:/";
    }

    // Retrieve the user details using the determined email
    Utilisateur utilisateur = utilisateurService.getUtilisateur(userEmail);
    if (utilisateur == null) {
        model.addAttribute("error", "Utilisateur introuvable pour l'email : " + userEmail);
        return "error"; // Ensure you have an "error.html" page
    }

    model.addAttribute("utilisateur", utilisateur);
    return "view-modif";
}



    @PostMapping("/modif")
    public String mettreAJourProfil(@ModelAttribute("activeUser") Utilisateur utilisateur, Model model) {
        // Retrieve the existing user from the database (preserving the password)
        Utilisateur existingUser = utilisateurService.getUtilisateur(utilisateur.getEmail());
        if (existingUser != null) {
            utilisateur.setMotDePasse(existingUser.getMotDePasse());
        }

        // Save the updated user
        utilisateurService.updateUtilisateur(utilisateur);

        // Update the activeUser in the session with the new values
        model.addAttribute("activeUser", utilisateurService.getUtilisateur(utilisateur.getEmail()));

        // Redirect to the profile page
        return "redirect:/encheres/profil";
    }



    @PostMapping("/supprimer")
    public String supprimerCompte(@ModelAttribute("activeUser") Utilisateur userActif, SessionStatus sessionStatus) {
        if (userActif != null && userActif.getEmail() != null) {
            Utilisateur existingUser = utilisateurService.getUtilisateur(userActif.getEmail());
            if (existingUser != null) {
                utilisateurService.deleteUtilisateur(existingUser);
            }
        }

        // Réinitialiser la session
        sessionStatus.setComplete();

        return "redirect:/encheres";
    }
}
