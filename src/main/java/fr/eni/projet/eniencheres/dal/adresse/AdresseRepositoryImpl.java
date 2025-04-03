package fr.eni.projet.eniencheres.dal.adresse;

import fr.eni.projet.eniencheres.bo.Adresse;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
    public int update(Adresse adresse) {
        String sql = """
                UPDATE adresses
                SET rue = :rue, code_postal = :code_postal, ville = :ville
                WHERE no_adresse = :no_adresse
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rue", adresse.getRue());
        params.addValue("code_postal", adresse.getCodePostal());
        params.addValue("ville", adresse.getVille());
        params.addValue("no_adresse", adresse.getId());

        return jdbc.update(sql, params);
    }

    @Override
    public Long findAdresseByID(Adresse address) {
        String sql = "SELECT no_adresse FROM adresses WHERE ville = :ville AND code_postal = :code_postal AND rue = :rue";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ville", address.getVille());
        params.addValue("code_postal", address.getCodePostal());
        params.addValue("rue", address.getRue());


        try {
            return jdbc.queryForObject(sql, params, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return null; // L'adresse n'existe pas
        }
    }

    @Override
    public boolean isSharedAdresse(Long addressId, String pseudo) {
        String sql = """
                SELECT COUNT(*)
                FROM adresses a
                JOIN utilisateurs u ON a.no_adresse = u.no_adresse
                WHERE u.no_adresse = :no_adresse AND u.pseudo != :pseudo;
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("no_adresse", addressId);
        params.addValue("pseudo", pseudo);
        Integer count = jdbc.queryForObject(sql, params, Integer.class);

        return count != null && count > 0;
    }

}
