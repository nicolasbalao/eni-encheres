package fr.eni.projet.eniencheres.dal.categorie;

import fr.eni.projet.eniencheres.bo.Categorie;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieRepositoryImpl implements CategorieRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public CategorieRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Categorie> getCategories() {
        String sql = "SELECT * FROM categories";
        return jdbc.query(sql, new CategoriesRowMapper());
    }

    @Override
    public boolean categorieExists(Long id) {
        String sql = "SELECT COUNT(*) FROM categories WHERE no_categorie = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        Long count = jdbc.queryForObject(sql, params, Long.class);
        return count != null && count > 0;
    }
}
