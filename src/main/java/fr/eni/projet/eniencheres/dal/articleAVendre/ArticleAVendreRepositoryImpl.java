package fr.eni.projet.eniencheres.dal.articleAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleAVendreRepositoryImpl implements ArticleAVendreRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public ArticleAVendreRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(ArticleAVendre articleAVendre) {
        String sql = """
                INSERT INTO 
                    articles_a_vendre(nom_article, description, date_debut_encheres, date_fin_encheres, statut_enchere, prix_initial, id_utilisateur, no_categorie, no_adresse_retrait)
                    VALUES(:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :status_enchere, :prix_initial, :id_utilisateur, :no_categorie, :no_adresse_retrait)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nom_article", articleAVendre.getNom());
        params.addValue("description", articleAVendre.getDescription());
        params.addValue("date_debut_encheres", articleAVendre.getDateDebutEncheres());
        params.addValue("date_fin_encheres", articleAVendre.getDateFinEncheres());
        params.addValue("status_enchere", articleAVendre.getStatut().getCode());
        params.addValue("prix_initial", articleAVendre.getPrixInitial());
        params.addValue("id_utilisateur", articleAVendre.getVendeur().getPseudo());
        params.addValue("no_categorie", articleAVendre.getCategorie().getId());
        params.addValue("no_adresse_retrait", articleAVendre.getRetrait().getId());

        jdbc.update(sql, params);
    }
}
