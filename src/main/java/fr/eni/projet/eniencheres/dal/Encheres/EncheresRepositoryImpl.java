package fr.eni.projet.eniencheres.dal.Encheres;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;
import fr.eni.projet.eniencheres.dal.utilisateur.UtilisateurRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

@Repository
public class EncheresRepositoryImpl implements EncheresRepository {

    private final NamedParameterJdbcTemplate jdbc;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EncheresRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Enchere> read() {
        return jdbc.query("EXEC sp_get_encheres",new EncheresRowMapper());
    }

    @Override
    public List<Enchere> read(String articleName, String category, boolean isAchat, int achatSelect, int venteSelect, String pseudo) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nom_article", articleName);
        params.addValue("nom_libelle", category);
        params.addValue("statut_enchere", (isAchat ? achatSelect : venteSelect));
        params.addValue("is_achat", isAchat ? 1 : 0);
        params.addValue("pseudo", pseudo);

        return jdbc.query("EXEC sp_get_encheres :nom_article, :nom_libelle, :statut_enchere, :is_achat, :pseudo", params, new EncheresRowMapper());
    }
}
