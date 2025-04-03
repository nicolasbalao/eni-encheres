package fr.eni.projet.eniencheres.dal.articleAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
                    article_a_vendre(nom_article, description, date_debut_encheres, date_fin_encheres, statut_encheres, prix_initial, id_utilisateur, no_categorie, no_adresse_retrait)
                    VALUES(:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :status_encheres, :prix_initial, :id_utilisateur, :no_categorie, :no_adresse_retrait)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nom_article", articleAVendre.getNom());
        params.addValue("description", articleAVendre.getDescription());
        params.addValue("date_debut_encheres", articleAVendre.getDateDebutEncheres());
        params.addValue("date_fin_encheres", articleAVendre.getDateFinEncheres());
        params.addValue("status_encheres", articleAVendre.getStatut());
        params.addValue("prix_initial", articleAVendre.getPrixInitial());
        params.addValue("id_utilisiteur", articleAVendre.getVendeur().getPseudo());
        params.addValue("no_categorie", articleAVendre.getCategorie().getId());
        params.addValue("no_adresse", articleAVendre.getRetrait().getId());

        jdbc.update(sql, params);
    }
}
