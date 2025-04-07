package fr.eni.projet.eniencheres.bll.articleAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;

public interface ArticleAVendreService {
    public void sell(ArticleAVendre articleAVendre);

    public ArticleAVendre getArticleAVendre(Long id);

    public void cancel(ArticleAVendre articleAVendre);

}
