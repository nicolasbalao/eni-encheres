package fr.eni.projet.eniencheres.dal.ArticlesAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;

import java.util.List;

public interface ArticlesAVendreRepository {
    List<ArticleAVendre> read();
}
