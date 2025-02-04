package fr.eni.projet.projeteni.bll;

import fr.eni.projet.projeteni.bo.Utilisateur;
import fr.eni.projet.projeteni.dal.UtilisateurDao;
import fr.eni.projet.projeteni.exceptions.BusinessCode;
import fr.eni.projet.projeteni.exceptions.BusinessException;
import org.springframework.stereotype.Service;

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
    public int addUtilisateur(Utilisateur utilisateur) {
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


//    Validation d'un utilisateur

    private boolean validerUtilisateur(Utilisateur u, BusinessException be) {
        if (u == null) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_NULL);
            return false;
        }
        return true;
    }

//    Pseudo doit etre non nul, non vide, unique et ne contenir que des caractères alphanumérique

    private boolean validerPseudo(String pseudo, BusinessException be) {
        // Vérification de base : non null, non vide
        if (pseudo == null || pseudo.isBlank() ) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_BLANK);
            return false;
        }
        // Vérifie que le format soit valide
        if(!pseudo.matches("^[a-zA-Z0-9_-]+$")){
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_INVALIDE);
            return false;
        }

        // Vérifie que la taille du pseudo soit correct
        if (pseudo.length() > 10 || pseudo.length() < 1) {
            be.add(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_TAILLE);
        }

//        // Vérifier si le pseudo existe déjà en base
//        if (utilisateurDao.findByPseudo(pseudo).isPresent()) {
//            be.add(BusinessCode.VALIDATION_PSEUDO_DEJA_UTILISE);
//            return false;
//        }

        return true; // Si tout est bon, pseudo valide
    }

}
