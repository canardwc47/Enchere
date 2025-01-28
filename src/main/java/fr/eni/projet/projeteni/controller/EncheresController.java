package fr.eni.projet.projeteni.controller;


import fr.eni.projet.projeteni.bll.UtilisateurService;
import fr.eni.projet.projeteni.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

@RequestMapping("/encheres")
public class EncheresController {


    @GetMapping
    public String afficherUtilisateur(Model model) {

        return "view-accueil";

    }
}
