package fr.eni.projet.projeteni.bll;


import fr.eni.projet.projeteni.bo.ArticleVendu;
import fr.eni.projet.projeteni.dal.DaoArticleVendu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private DaoArticleVendu daoArticleVendu;

    public ArticleVenduServiceImpl(DaoArticleVendu daoArticleVendu) {
        this.daoArticleVendu = daoArticleVendu;
    }


    @Override
    public List<ArticleVendu> getAllArticleVendu() {
        return daoArticleVendu.read();
    }

    @Override
    public ArticleVendu getArticleVenduById(int id) {
        return daoArticleVendu.read(id);
    }

    @Override
    public int createArticleVendu(ArticleVendu articleVendu) {
        return daoArticleVendu.create(articleVendu);
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {
        daoArticleVendu.update(articleVendu);
    }

    @Override
    public void deleteArticleVendu(int id) {
        daoArticleVendu.delete(id);
    }

    @Override
    public void deleteArticleVendu(ArticleVendu articleVendu) {
        daoArticleVendu.delete(articleVendu);
    }


}
