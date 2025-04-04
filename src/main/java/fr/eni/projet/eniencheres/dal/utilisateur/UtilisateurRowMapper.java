package fr.eni.projet.eniencheres.dal.utilisateur;

import fr.eni.projet.eniencheres.bo.Adresse;
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
        utilisateur.setCredit(rs.getInt("credit"));

        Adresse adresse = new Adresse();
        adresse.setRue(rs.getString("rue"));
        adresse.setVille(rs.getString("ville"));
        adresse.setCodePostal(rs.getString("code_postal"));
        adresse.setId(rs.getLong("no_adresse"));

        utilisateur.setAdresse(adresse);

        return utilisateur;
    }
}
