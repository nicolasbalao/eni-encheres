package fr.eni.projet.eniencheres.dal.utilisateur;

import fr.eni.projet.eniencheres.bo.Utilisateur;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

    private final NamedParameterJdbcTemplate jdbc;


    public UtilisateurRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(Utilisateur user) {

        String sql = "INSERT INTO utilisateurs(pseudo, nom, prenom, email, telephone, mot_de_passe, credit, no_adresse) VALUES(:pseudo, :nom, :prenom, :email, :telephone, :mot_de_passe, 10, :no_adresse)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pseudo", user.getPseudo());
        params.addValue("nom", user.getNom());
        params.addValue("prenom", user.getPrenom());
        params.addValue("email", user.getEmail());
        params.addValue("telephone", user.getTelephone());
        params.addValue("mot_de_passe", user.getMotDePasse());
        params.addValue("no_adresse", user.getAdresse().getId());
        jdbc.update(sql, params);
    }

    @Override
    public boolean existByPseudo(String pseudo) {
        String sql = "SELECT COUNT(*) FROM utilisateurs WHERE pseudo = :pseudo";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("pseudo", pseudo);
        Integer count = jdbc.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public boolean existByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM utilisateurs WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);
        Integer count = jdbc.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public Utilisateur profileByPseudo(String pseudo) {
        String sql = "SELECT prenom, nom, email, telephone, pseudo FROM utilisateurs WHERE pseudo = :pseudo";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("pseudo", pseudo);

        try {
            return jdbc.queryForObject(sql, params, new BeanPropertyRowMapper<>(Utilisateur.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Utilisateur profileDetailsByPseudo(String pseudo) {
        String sql = """
                SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, a.no_adresse
                FROM utilisateurs u
                JOIN adresses a ON a.no_adresse = u.no_adresse
                WHERE u.pseudo = :pseudo
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("pseudo", pseudo);

        return jdbc.queryForObject(sql, params, new UtilisateurRowMapper());
    }

    @Override
    public int update(Utilisateur user) {
        String sql = """
                UPDATE utilisateurs
                SET prenom = :prenom, nom = :nom, email = :email, telephone = :telephone
                WHERE pseudo = :pseudo
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pseudo", user.getPseudo());
        params.addValue("prenom", user.getPrenom());
        params.addValue("nom", user.getNom());
        params.addValue("email", user.getEmail());
        params.addValue("telephone", user.getTelephone());

        return jdbc.update(sql, params);
    }


}
