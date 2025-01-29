package fr.eni.projet.projeteni.bll;

import fr.eni.projet.projeteni.bo.Categorie;
import fr.eni.projet.projeteni.dal.DaoCategories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriServicelmp implements CategoriService {
    private DaoCategories daoCategories;

    public CategoriServicelmp(DaoCategories daoCategories) {
        this.daoCategories = daoCategories;
    }

    @Override
    public void addCategories(Categorie categori) {
        daoCategories.create(categori);
    }

    @Override
    public void removeCategories(int id) {
        daoCategories.delete(id);
    }

    @Override
    public List<Categorie> getCategories() {
        return daoCategories.read();
    }

    @Override
    public Categorie getNoCategorie(int id) {
        return daoCategories.read(id) ;
    }

    @Override
    public void update(Categorie categorie) {
        daoCategories.update(categorie);
    }

}
