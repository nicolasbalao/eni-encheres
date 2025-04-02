package fr.eni.projet.eniencheres.bll.Encheres;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;

import java.util.List;

public interface EncheresService {
    public List<Enchere> consulterArticlesAVendre();
}
