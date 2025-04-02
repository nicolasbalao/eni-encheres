package fr.eni.projet.eniencheres.dal.rowMapper;

import fr.eni.projet.eniencheres.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRowMapper implements RowMapper<Utilisateur> {
    @Override
    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo(rs.getString("pseudo"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setTelephone(rs.getString("telephone"));

        return utilisateur;
    }
}
