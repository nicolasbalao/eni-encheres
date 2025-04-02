package fr.eni.projet.eniencheres.dal.Encheres;

import fr.eni.projet.eniencheres.bo.Adresse;
import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EncheresRowMapper implements RowMapper<Enchere> {
    @Override
    public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enchere e = new Enchere();

        Double montant_enchere = rs.getObject("montant_enchere", Double.class);
        e.setMontant(montant_enchere != null ? montant_enchere : rs.getDouble("prix_initial"));

        Date sqlDate = rs.getDate("date_enchere");
        e.setDate(sqlDate != null ? sqlDate.toLocalDate() : null);

        Utilisateur u = new Utilisateur();

        u.setPseudo(rs.getString("pseudo"));
        u.setPrenom(rs.getString("prenom"));
        u.setNom(rs.getString("nom"));
        u.setEmail(rs.getString("email"));


        u.setTelephone(rs.getString("telephone"));
        u.setAdmin(rs.getBoolean("administrateur"));
        u.setCredit(rs.getDouble("credit"));

        Adresse a = new Adresse();

        a.setId(rs.getLong("no_adresse"));
        a.setRue(rs.getString("rue"));
        a.setCodePostal(rs.getString("code_postal"));
        a.setVille(rs.getString("ville"));

        u.setAdresse(a);


        ArticleAVendre aav = new ArticleAVendre();
        aav.setNom(rs.getString("nom_article"));
        aav.setDescription(rs.getString("description"));
        aav.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
        aav.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
        aav.setStatut(rs.getDouble(("statut_enchere")));
        aav.setPrixInitial(rs.getDouble("prix_initial"));
        aav.setPrixFinal(rs.getDouble("prix_vente"));

        aav.setRetrait(new Adresse());
        aav.setVendeur(u);

        e.setArticleAVendre(aav);

        return e;
    }
}
