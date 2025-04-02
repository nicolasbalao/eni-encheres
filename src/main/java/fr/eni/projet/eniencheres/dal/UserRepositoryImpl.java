package fr.eni.projet.eniencheres.dal;

import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dal.interfaces.UserRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbc;


    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbc) {
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
}
