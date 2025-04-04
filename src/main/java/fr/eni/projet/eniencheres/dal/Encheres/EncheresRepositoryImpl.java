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
    public void updateStatusEnchere(long id, Number status) {
        String sql = """
                UPDATE ARTICLES_A_VENDRE
                SET statut_enchere = :status
                WHERE no_article = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("status", status)
                .addValue("id", id);
    }

    @Override
    @Transactional
    public Enchere livrerEnchere(long id) {
        Enchere enchere = find(id);
        if (enchere != null) {

            Utilisateur acheteur = utilisateurRepository.profileByPseudo(enchere.getAcquereur().getPseudo());
            Utilisateur vendeur = utilisateurRepository.profileByPseudo(enchere.getArticleAVendre().getVendeur().getPseudo());
            Number credit_to_transfert = enchere.getMontant();

            if (acheteur.getCredit().intValue() < credit_to_transfert.intValue()) {
                throw new IllegalArgumentException("Crédit insuffisant de l'acheteur pour effectuer la transaction.");
            }

            utilisateurRepository.updateCreditUser(acheteur.getPseudo(), acheteur.getCredit().intValue() - credit_to_transfert.intValue());
            utilisateurRepository.updateCreditUser(vendeur.getPseudo(), vendeur.getCredit().intValue() + credit_to_transfert.intValue());
            updateStatusEnchere(enchere.getArticleAVendre().getId(), 3);

            return this.find(enchere.getArticleAVendre().getId());
        }
        return new Enchere();
    }
}
