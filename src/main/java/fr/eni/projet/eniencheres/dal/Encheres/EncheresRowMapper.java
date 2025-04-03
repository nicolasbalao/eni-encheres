package fr.eni.projet.eniencheres.dal.Encheres;

import fr.eni.projet.eniencheres.bo.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EncheresRowMapper implements RowMapper<Enchere> {
    @Override
    public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enchere e = new Enchere();

        Double montant_enchere = rs.getObject("montant_enchere", Double.class);
        Double prix_initial = rs.getObject("prix_initial", Double.class);

        e.setMontant(montant_enchere != null  && montant_enchere > prix_initial ? montant_enchere : prix_initial);

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

        Categorie c = new Categorie();
        c.setId(rs.getLong("id_categorie"));
        c.setLibelle(rs.getString("categorie").replaceAll(" ", ""));

        Adresse retrait = new Adresse();
        retrait.setId(rs.getLong("retrait_id"));
        retrait.setVille(rs.getString("retrait_ville"));
        retrait.setRue(rs.getString("retrait_rue"));
        retrait.setCodePostal(rs.getString("retrait_code_postal"));

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
        aav.setCategorie(c);
        aav.setRetrait(retrait);

        e.setArticleAVendre(aav);

        return e;
    }
}
