package fr.eni.projet.eniencheres.bll.articleAVendre;

import fr.eni.projet.eniencheres.bo.Adresse;
import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.StatutEnchere;
import fr.eni.projet.eniencheres.dal.adresse.AdresseRepository;
import fr.eni.projet.eniencheres.dal.articleAVendre.ArticleAVendreRepository;
import fr.eni.projet.eniencheres.dal.categorie.CategorieRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ArticleAVendreSerivceImpl implements ArticleAVendreService {
    private final ArticleAVendreRepository articleAVendreRepository;
    private final AdresseRepository adresseRepository;
    private final CategorieRepository categorieRepository;

    public ArticleAVendreSerivceImpl(ArticleAVendreRepository articleAVendreRepository, AdresseRepository adresseRepository, CategorieRepository categorieRepository) {
        this.articleAVendreRepository = articleAVendreRepository;
        this.adresseRepository = adresseRepository;
        this.categorieRepository = categorieRepository;
    }

    @Override
    public void sell(ArticleAVendre articleAVendre) {
        // Check if adresse exist
        Adresse adresse = adresseRepository.findAdresseById(articleAVendre.getRetrait().getId());

        if (adresse == null) {
            throw new BusinessException("article.sell.retrait.notFound");
        }
        // Check if categorie exist
        boolean categorieExist = categorieRepository.categorieExists(articleAVendre.getCategorie().getId());
        if (!categorieExist) {
            throw new BusinessException("article.sell.categorie.notFound");
        }

        //STATUT_ENCHERE:  0 ( non commencée), 1 (en cours), 2 (clôturée), 3 (livrée), 100 (annulée)
        if (LocalDate.now().isEqual(articleAVendre.getDateDebutEncheres())) {
            articleAVendre.setStatut(StatutEnchere.EN_COURS);
        } else {
            articleAVendre.setStatut(StatutEnchere.NON_COMMENCEE);
        }

        articleAVendreRepository.insert(articleAVendre);
    }

    @Override
    public ArticleAVendre getArticleAVendre(Long id) {
        ArticleAVendre articleAVendre = articleAVendreRepository.findById(id);

        if (articleAVendre == null) {
            throw new BusinessException("article.get.articleAVendre.notFound");
        }

        return articleAVendre;
    }

    @Override
    public void cancel(ArticleAVendre articleAVendre) {
        articleAVendre.setStatut(StatutEnchere.ANNULEE);
        articleAVendreRepository.updateStatut(articleAVendre);
    }


}
