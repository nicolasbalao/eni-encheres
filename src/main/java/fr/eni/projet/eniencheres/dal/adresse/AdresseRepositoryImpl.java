package fr.eni.projet.eniencheres.dal.adresse;

import fr.eni.projet.eniencheres.bo.Adresse;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AdresseRepositoryImpl implements AdresseRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public AdresseRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(Adresse address) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO adresses(rue, code_postal, ville) VALUES (:rue, :code_postal, :ville)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rue", address.getRue());
        params.addValue("code_postal", address.getCodePostal());
        params.addValue("ville", address.getVille());

        jdbc.update(sql, params, keyHolder);

        if (keyHolder.getKey() != null) {
            address.setId(keyHolder.getKey().longValue());
        }
    }
}
