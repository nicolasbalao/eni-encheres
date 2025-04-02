package fr.eni.projet.eniencheres.bll.Encheres;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;

import java.util.List;
import java.util.Optional;

public interface EncheresService {
    public List<Enchere> consulterArticlesAVendre();
    public List<Enchere> consulterArticlesAVendre(String articleName, String category, boolean isAchat, int achatSelect, int venteSelect, String pseudo);
}
