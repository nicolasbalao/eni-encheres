package fr.eni.projet.eniencheres.dal.articleAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;

public interface ArticleAVendreRepository {
    public void insert(ArticleAVendre articleAVendre);

    public ArticleAVendre findById(Long id);

    public void updateStatut(ArticleAVendre articleAVendre);

    public int update(ArticleAVendre articleAVendre);
}
