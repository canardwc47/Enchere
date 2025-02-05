package fr.eni.projet.projeteni.dal;

import fr.eni.projet.projeteni.bo.Images;

import java.util.List;

public interface ImagesDao {

    List<Images> readAll();
    Images readById(int id);
    int create(Images images);
    void update(Images images);
    void delete(int id);
}
