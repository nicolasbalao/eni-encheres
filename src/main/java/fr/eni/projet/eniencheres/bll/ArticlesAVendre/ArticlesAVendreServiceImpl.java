package fr.eni.projet.eniencheres.bll.ArticlesAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.dal.ArticlesAVendre.ArticlesAVendreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesAVendreServiceImpl implements ArticlesAVendreService {
    private ArticlesAVendreRepository articlesAVendreRepository;

    public ArticlesAVendreServiceImpl(ArticlesAVendreRepository articlesAVendreRepository) {
        super();
        this.articlesAVendreRepository = articlesAVendreRepository;
    }

    @Override
    public List<ArticleAVendre> consulterArticlesAVendre() {
        return articlesAVendreRepository.read();
    }
}
