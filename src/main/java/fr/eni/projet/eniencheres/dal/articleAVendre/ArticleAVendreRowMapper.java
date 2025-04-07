package fr.eni.projet.eniencheres.dal.articleAVendre;

import fr.eni.projet.eniencheres.bo.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ArticleAVendreRowMapper implements RowMapper<ArticleAVendre> {
    public ArticleAVendre mapRow(ResultSet rs, int rowNum) throws SQLException {
        ArticleAVendre articleAVendre = new ArticleAVendre();
        articleAVendre.setId(rs.getLong("no_article"));
        articleAVendre.setNom(rs.getString("nom_article"));
        articleAVendre.setDescription(rs.getString("description"));
        articleAVendre.setDateDebutEncheres(LocalDate.parse(rs.getString("date_debut_encheres")));
        articleAVendre.setDateFinEncheres(LocalDate.parse(rs.getString("date_fin_encheres")));
        articleAVendre.setStatut(StatutEnchere.fromCode(rs.getInt("statut_enchere")));
        articleAVendre.setPrixInitial(rs.getDouble("prix_initial"));
        articleAVendre.setPrixFinal(rs.getDouble("prix_vente"));

        Categorie categorie = new Categorie();
        categorie.setId(rs.getLong("no_categorie"));
        articleAVendre.setCategorie(categorie);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo(rs.getString("id_utilisateur"));
        articleAVendre.setVendeur(utilisateur);

        Adresse adresse = new Adresse();
        adresse.setId(rs.getLong("no_adresse_retrait"));
        articleAVendre.setRetrait(adresse);

        return articleAVendre;
    }
}
