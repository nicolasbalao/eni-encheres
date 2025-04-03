package fr.eni.projet.eniencheres.dal.articleAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ArticleAVendreRepositoryImpl implements ArticleAVendreRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public ArticleAVendreRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(ArticleAVendre articleAVendre) {
        String sql = """
                INSERT INTO 
                    article_a_vendre(nom_article, description, date_debut_encheres, date_fin_encheres, statut_encheres, prix_initial, prix_vente, id_utilisateur, no_categorie, no_adresse_retrait)
                    VALUES(:nom_article, :description, :date_debut_encheres, :date_fin_enchres, :status_encheres, :prix_initial,:prix_vente, :id_utilisateur, :no_categorie, :no_adresse_retrait)
                """;
        

    }
}
