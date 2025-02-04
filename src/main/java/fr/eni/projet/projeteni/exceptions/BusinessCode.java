package fr.eni.projet.projeteni.exceptions;

public class BusinessCode {

    // Clefs de validation des BO
    public static final String VALIDATION_UTILISATEUR_NULL = "validation.utilisateur.null";
    public static final String VALIDATION_UTILISATEUR_PSEUDO_INVALIDE = "validation.utilisateur.pseudo.invalide";
    public static final String VALIDATION_UTILISATEUR_PSEUDO_BLANK = "validation.utilisateur.pseudo.blank";
    public static final String VALIDATION_UTILISATEUR_PSEUDO_TAILLE = "validation.utilisateur.pseudo.taille";
    public static final String VALIDATION_PSEUDO_DEJA_UTILISE = "validation.utilisateur.pseudo.utilise";

    public static final String VALIDATION_UTILISATEUR_EMAIL_BLANK= "validation.utilisateur.email.blank";
    public static final String VALIDATION_UTILISATEUR_EMAIL_INVALIDE = "validation.utilisateur.email.invalide";
    public static final String VALIDATION_UTILISATEUR_EMAIL_DEJA_UTILISE = "validation.utilisateur.email.deja.utilise";
    public static final String VALIDATION_UTILISATEUR_EMAIL_TROP_LONG = "validation.utilisateur.email.trop.long";

    public static final String VALIDATION_UTILISATEUR_NOM_BLANK = "validation.nom.blank";
    public static final String VALIDATION_UTILISATEUR_NOM_TROP_LONG = "validation.nom.trop.long";
    public static final String VALIDATION_UTILISATEUR_NOM_INVALIDE = "validation.utilisateur.nom.invalide";

    public static final String VALIDATION_UTILISATEUR_PRENOM_BLANK = "validation.utilisateur.prenom.blank";
    public static final String VALIDATION_UTILISATEUR_PRENOM_TROP_LONG = "validation.utilisateur.prenom.trop.long";
    public static final String VALIDATION_UTILISATEUR_PRENOM_INVALIDE = "validation.utilisateur.prenom.invalide";

    public static final String VALIDATION_UTILISATEUR_CP_NULL="validation.utilisateur.cp.null";
    public static final String VALIDATION_UTILISATEUR_CP_INVALIDE="validation.utilisateur.cp.invalide ";

    public static final String VALIDATION_UTILISATEUR_NUMERO_RUE_NULL = "validation.utilisateur.numero.null";
    public static final String VALIDATION_UTILISATEUR_NUMERO_RUE_INVALIDE = "validation.utilisateur.numero.invalide";


    public static final String VALIDATION_UTILISATEUR_TELEPHONE_NULL = "validation.utilisateur.telephone.null";
    public static final String VALIDATION_UTILISATEUR_TELEPHONE_INVALIDE = "validation.utilisateur.telephone.invalide";

    public static final String VALIDATION_UTILISATEUR_VILLE_NULL = "validation.utilisateur.ville.null";
    public static final String VALIDATION_UTILISATEUR_VILLE_INVALIDE = "validation.utilisateur.ville.invalide";

    public static final String VALIDATION_UTILISATEUR_MDP_NULL = "validation.utilisateur.mdp.null";
    public static final String VALIDATION_UTILISATEUR_MDP_INVALIDE = "validation.utilisateur.mdp.invalide";

    public static final String VALIDATION_UTILISATEUR_CONFIRMATION_MDP_NULL = "validation.utilisateur.confirmation.mdp.null";
    public static final String VALIDATION_UTILISATEUR_CONFIRMATION_MDP_DIFFERENT= "validation.utilisateur.confirmation.mdp.different";

    public static final String VALIDATION_FILM_INCONNU = "validation.film.inconnu";
    public static final String VALIDATION_FILM_ID_INCONNU = "validation.film.id.inconnu";
}
