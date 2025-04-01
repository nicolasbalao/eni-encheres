package fr.eni.projet.eniencheres.dal;

import fr.eni.projet.eniencheres.bo.Address;
import fr.eni.projet.eniencheres.dal.interfaces.AddressRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public AddressRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(Address address) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO adresses(rue, code_postal, ville) VALUES (:rue, :code_postal, :ville)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rue", address.getStreet());
        params.addValue("code_postal", address.getZipcode());
        params.addValue("ville", address.getCity());

        jdbc.update(sql, params, keyHolder);

        if (keyHolder.getKey() != null) {
            address.setId(keyHolder.getKey().intValue());
        }
    }
}
