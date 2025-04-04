package fr.eni.projet.eniencheres.dal.adresse;

import fr.eni.projet.eniencheres.bo.Adresse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresseRowMapper implements RowMapper<Adresse> {
    @Override
    public Adresse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Adresse adresse = new Adresse();
        adresse.setId(rs.getLong("no_adresse"));
        adresse.setRue(rs.getString("rue"));
        adresse.setVille(rs.getString("ville"));
        adresse.setCodePostal(rs.getString("code_postal"));

        return adresse;
    }
}
