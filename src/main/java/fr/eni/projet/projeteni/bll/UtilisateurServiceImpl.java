package fr.eni.projet.projeteni.bll;

import fr.eni.projet.projeteni.bo.Utilisateur;
import fr.eni.projet.projeteni.dal.UtilisateurDao;
import fr.eni.projet.projeteni.exceptions.BusinessCode;
import fr.eni.projet.projeteni.exceptions.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurDao utilisateurDao;

    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public Utilisateur getUtilisateur(String email) {
        return utilisateurDao.read(email);
    }

    @Override
    public Utilisateur getUtilisateur(int no_utilisateur) {
        return utilisateurDao.read(no_utilisateur);
    }

    @Override
    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurDao.read();
    }


    @Override
    @Transactional
    public int addUtilisateur(Utilisateur utilisateur) {
        BusinessException be = new BusinessException();
        boolean isValid = true;

        isValid &= validerUtilisateur(utilisateur, be);
        isValid &= validerPseudo(utilisateur.getPseudo(), be);
        isValid &= validerNom(utilisateur.getNom(), be);
        isValid &= validerPrenom(utilisateur.getPrenom(), be);
        isValid &= validerEmail(utilisateur.getEmail(), be);
        isValid &= validerCodePostal(utilisateur.getCodePostal(), be);
        isValid &= validerNumeroRue(utilisateur.getRue(), be);
        isValid &= validerNumeroTelephone(utilisateur.getTelephone(), be);
        isValid &= validerVille(utilisateur.getVille(), be);
        isValid &= validerMotDePasse(utilisateur.getMotDePasse(), be);

        if (!isValid) {
            throw be;  // Empêche l'ajout en base si la validation échoue
        }

        return utilisateurDao.create(utilisateur);
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.update(utilisateur);
    }

    @Override
    public void deleteUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.delete(utilisateur);
    }

    // Vérifie que l'objet utilisateur n'est pas null
    private boolean validerUtilisateur(Utilisateur u, BusinessException be) {
        if (u == null) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NULL);
            return false;
        }
        return true;
    }

    // Validation du pseudo
    private boolean validerPseudo(String pseudo, BusinessException be) {
        if (pseudo == null || pseudo.isBlank()) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_BLANK);
            return false;
        }
        if (!pseudo.matches("^[a-zA-Z0-9_-]+$")) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_INVALIDE);
            return false;
        }
        if (pseudo.length() > 10 || pseudo.length() < 1) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_TAILLE);
            return false;
        }
        if (utilisateurDao.read().stream().anyMatch(user -> user.getPseudo().equals(pseudo))) {
            be.add(BusinessCode.VALIDATION_PSEUDO_DEJA_UTILISE);
            return false;
        }
        return true;
    }

    // Validation du nom
    private boolean validerNom(String nom, BusinessException be) {
        if (nom == null || nom.isBlank()) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NOM_BLANK);
            return false;
        }
        if (nom.length() > 3) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NOM_TROP_LONG);
            return false;
        }
        if (!nom.matches("^[a-zA-ZÀ-ÿ ]+$")) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NOM_INVALIDE);
            return false;
        }
        return true;
    }

    // Validation du prénom
    private boolean validerPrenom(String prenom, BusinessException be) {
        if (prenom == null || prenom.isBlank()) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PRENOM_BLANK);
            return false;
        }
        if (prenom.length() > 100) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PRENOM_TROP_LONG);
            return false;
        }
        if (!prenom.matches("^[a-zA-ZÀ-ÿ ]+$")) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PRENOM_INVALIDE);
            return false;
        }
        return true;
    }

    // Validation de l'email
    private boolean validerEmail(String email, BusinessException be) {
        if (email == null || email.isBlank()) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_EMAIL_BLANK);
            return false;
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_EMAIL_INVALIDE);
            return false;
        }
        if (email.length() > 254) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_EMAIL_TROP_LONG);
            return false;
        }
        if (utilisateurDao.read().stream().anyMatch(user -> user.getEmail().equals(email))) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_EMAIL_DEJA_UTILISE);
            return false;
        }
        return true;
    }

    // Validation du code postal
    private boolean validerCodePostal(int codePostal, BusinessException be) {
        if (codePostal < 10000 || codePostal > 99999) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_CP_INVALIDE);
            return false;
        }
        return true;
    }

    // Validation de l'adresse
    private boolean validerNumeroRue(String numeroRue, BusinessException be) {
        if (numeroRue == null || numeroRue.isBlank()) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NUMERO_RUE_NULL);
            return false;
        }
        if (!numeroRue.matches("^\\d+[A-Za-z]?$")) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NUMERO_RUE_INVALIDE);
            return false;
        }
        return true;
    }

    // Validation du numéro de téléphone
    private boolean validerNumeroTelephone(String numeroTelephone, BusinessException be) {
        if (numeroTelephone == null || numeroTelephone.isBlank()) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_TELEPHONE_NULL);
            return false;
        }
        if (!numeroTelephone.matches("^\\+?[0-9]{10,15}$")) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_TELEPHONE_INVALIDE);
            return false;
        }
        return true;
    }

    // Validation de la ville
    private boolean validerVille(String ville, BusinessException be) {
        if (ville == null || ville.isBlank()) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_VILLE_NULL);
            return false;
        }
        if (!ville.matches("^[a-zA-ZÀ-ÿ\\- ]{2,50}$")) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_VILLE_INVALIDE);
            return false;
        }
        return true;
    }

    // Validation du mot de passe
    private boolean validerMotDePasse(String motDePasse, BusinessException be) {
        if (motDePasse == null || motDePasse.isBlank()) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_NULL);
            return false;
        }
        if (!motDePasse.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,64}$")) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_MDP_INVALIDE);
            return false;
        }
        return true;
    }

}



