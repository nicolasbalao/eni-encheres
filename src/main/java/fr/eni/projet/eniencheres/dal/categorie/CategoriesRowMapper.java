package fr.eni.projet.eniencheres.dal.categorie;

import fr.eni.projet.eniencheres.bo.Categorie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesRowMapper implements RowMapper<Categorie> {
    @Override
    public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Categorie categorie = new Categorie();
        categorie.setId(rs.getLong("no_categorie"));
        categorie.setLibelle(rs.getString("libelle"));
        return categorie;
    }
}
