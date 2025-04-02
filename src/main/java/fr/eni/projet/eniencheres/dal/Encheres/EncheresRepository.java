package fr.eni.projet.eniencheres.dal.Encheres;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;

import java.util.List;

public interface EncheresRepository {
    List<Enchere> read();
    List<Enchere> read(String articleName, String category, boolean isAchat, int achatSelect, int venteSelect, String pseudo);
}
