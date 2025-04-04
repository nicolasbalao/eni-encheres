package fr.eni.projet.eniencheres.dal.Encheres;

import fr.eni.projet.eniencheres.bll.Encheres.EncheresService;
import fr.eni.projet.eniencheres.bo.Enchere;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dal.utilisateur.UtilisateurRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class EncheresRepositoryImpl implements EncheresRepository {

    private final NamedParameterJdbcTemplate jdbc;
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EncheresRepositoryImpl(NamedParameterJdbcTemplate jdbc, UtilisateurRepository utilisateurRepository) {
        this.jdbc = jdbc;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<Enchere> read() {
        return jdbc.query("EXEC sp_get_encheres",new EncheresRowMapper());
    }

    @Override
    public List<Enchere> read(String articleName, String category, boolean isAchat, int achatSelect, int venteSelect, String pseudo) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nom_article", articleName);
        params.addValue("nom_libelle", category);
        params.addValue("statut_enchere", (isAchat ? achatSelect : venteSelect));
        params.addValue("is_achat", isAchat ? 1 : 0);
        params.addValue("pseudo", pseudo);

        return jdbc.query("EXEC sp_get_encheres 0,NULL, :nom_article, :nom_libelle, :statut_enchere, :is_achat, :pseudo", params, new EncheresRowMapper());
    }

    @Override
    public Enchere find(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("no_article", id);
        try {
            return jdbc.queryForObject("EXEC sp_get_encheres 1,:no_article", params, new EncheresRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void create(long id_article, String acheteur, int montant) {
        String sql = """
        INSERT INTO ENCHERES (id_utilisateur, no_article, montant_enchere, date_enchere)
        VALUES (:pseudo, :id_article, :montant_enchere, :date_enchere)
        """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("pseudo", acheteur)
                .addValue("id_article", id_article)
                .addValue("montant_enchere", montant)
                .addValue("date_enchere", new Date());

        jdbc.update(sql, params);
    }


    @Override
    @Transactional
    public Enchere encherire(String pseudoAcheteur, long idArticle, int montant) {
        Enchere enchere = find(idArticle);
        if (enchere != null) {

            Utilisateur acheteur = utilisateurRepository.profileByPseudo(pseudoAcheteur);

            if (acheteur.getCredit() < montant) {
                throw new IllegalArgumentException("Crédit insuffisant de l'acheteur pour effectuer la transaction.");
            }
            else if(montant < enchere.getMontant()){
                throw new IllegalArgumentException("Crédit inférieur a l'offre la plus haute.");
            }

            // on crée l'enchère
            this.create(enchere.getArticleAVendre().getId(), pseudoAcheteur, montant);

            // on débite l'acheteur
            utilisateurRepository.updateCreditUser(acheteur.getPseudo(), acheteur.getCredit() - montant);

            // on crédite l'ancien acheteur
            if(enchere.getAcquereur() != null && enchere.getAcquereur().getPseudo() != null && !enchere.getAcquereur().getPseudo().isEmpty()){
                Utilisateur ancien_acheteur = utilisateurRepository.profileByPseudo(enchere.getAcquereur().getPseudo());
                utilisateurRepository.updateCreditUser(ancien_acheteur.getPseudo(), ancien_acheteur.getCredit() + enchere.getMontant());
            }

            return this.find(enchere.getArticleAVendre().getId());
        }
        return new Enchere();
    }

    @Override
    public void updateStatusEnchere(long id, Number status) {
        String sql = """
                UPDATE ARTICLES_A_VENDRE
                SET statut_enchere = :status
                WHERE no_article = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("status", status)
                .addValue("id", id);

        jdbc.update(sql, params);
    }

    @Override
    @Transactional
    public Enchere livrerEnchere(long id) {
        Enchere enchere = find(id);
        if (enchere != null) {
            if(enchere.getArticleAVendre().getStatut().intValue() != 2){
                throw new IllegalArgumentException("Il semblerait que le vente n'est pas en statut cloturé.");
            }

            // on récupère le vendeur
            Utilisateur vendeur = utilisateurRepository.profileByPseudo(enchere.getArticleAVendre().getVendeur().getPseudo());

            // on crédite le vendeur
            utilisateurRepository.updateCreditUser(vendeur.getPseudo(), vendeur.getCredit() + enchere.getMontant());

            // on met à jour le status de l'enchère
            updateStatusEnchere(enchere.getArticleAVendre().getId(), 3);

            return this.find(enchere.getArticleAVendre().getId());
        }
        return new Enchere();
    }

}
