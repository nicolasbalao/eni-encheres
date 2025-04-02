package fr.eni.projet.eniencheres.dal.Encheres;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

@Repository
public class EncheresRepositoryImpl implements EncheresRepository {

    private final String FIND_ALL_OFFLINE = """
        SELECT
           A.date_debut_encheres,
           A.date_fin_encheres,
           A.description,
           A.nom_article,
           A.prix_initial,
           A.prix_vente,
           A.statut_enchere,
           U.nom,
           U.prenom,
           U.pseudo,
           U.email,
           U.credit,
           U.telephone,
           U.administrateur,
           AD.no_adresse,
           AD.rue,
           AD.code_postal,
           AD.ville,
           C.no_categorie as id_categorie,
           C.libelle as categorie,
           MAX(E.montant_enchere) as montant_enchere,
           E.date_enchere
           FROM ARTICLES_A_VENDRE A
           LEFT JOIN ENCHERES E ON A.no_article = E.no_article
           INNER JOIN UTILISATEURS U ON A.id_utilisateur = U.pseudo
           INNER JOIN ADRESSES AD ON U.no_adresse = AD.no_adresse
           INNER JOIN CATEGORIES C ON A.no_categorie = C.no_categorie
           
           WHERE A.prix_vente IS NULL AND A.statut_enchere = 1
           GROUP BY
               A.date_debut_encheres,
               A.date_fin_encheres,
               A.description,
               A.nom_article,
               A.prix_initial,
               A.prix_vente,
               A.statut_enchere,
               U.nom,
               U.prenom,
               U.pseudo,
               U.email,
               U.credit,
               U.telephone,
               U.administrateur,
               AD.no_adresse,
               AD.rue,
               AD.code_postal,
               AD.ville,
               C.no_categorie,
               C.libelle,
               E.date_enchere;
    """;


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Enchere> read() {
        List<Enchere> e = namedParameterJdbcTemplate.query(FIND_ALL_OFFLINE, new EncheresRowMapper());
        return e;
    }
}
