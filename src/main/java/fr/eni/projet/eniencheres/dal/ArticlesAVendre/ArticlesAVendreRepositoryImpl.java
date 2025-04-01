package fr.eni.projet.eniencheres.dal.ArticlesAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

@Repository
public class ArticlesAVendreRepositoryImpl implements ArticlesAVendreRepository {

    private final String FIND_ALL_OFFLINE = """
    SELECT
        ARTICLES_A_VENDRE.date_debut_encheres,
        ARTICLES_A_VENDRE.date_fin_encheres,
        ARTICLES_A_VENDRE.description,
        ARTICLES_A_VENDRE.nom_article,
        ARTICLES_A_VENDRE.prix_initial,
        ARTICLES_A_VENDRE.prix_vente,
        ARTICLES_A_VENDRE.statut_enchere,
        UTILISATEURS.nom,
        UTILISATEURS.prenom,
        UTILISATEURS.pseudo,
        UTILISATEURS.email,
        UTILISATEURS.credit,
        UTILISATEURS.telephone,
        UTILISATEURS.administrateur,
        ADRESSES.no_adresse,
        ADRESSES.rue,
        ADRESSES.code_postal,
        ADRESSES.ville
    FROM ARTICLES_A_VENDRE
    INNER JOIN UTILISATEURS ON ARTICLES_A_VENDRE.id_utilisateur = UTILISATEURS.pseudo
    INNER JOIN ADRESSES ON UTILISATEURS.no_adresse = ADRESSES.no_adresse
    WHERE ARTICLES_A_VENDRE.prix_vente IS NULL;
    """;


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<ArticleAVendre> read() {
        List<ArticleAVendre> f = namedParameterJdbcTemplate.query(FIND_ALL_OFFLINE, new ArticlesAVendreRowMapper());
        return f;
    }
}
