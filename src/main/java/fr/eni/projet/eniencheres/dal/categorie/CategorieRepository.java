package fr.eni.projet.eniencheres.dal.categorie;

import fr.eni.projet.eniencheres.bo.Categorie;

import java.util.List;

public interface CategorieRepository {
    List<Categorie> getCategories();

    public boolean categorieExists(Long id);
}
