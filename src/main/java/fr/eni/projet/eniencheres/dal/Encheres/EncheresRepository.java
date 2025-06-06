package fr.eni.projet.eniencheres.dal.Encheres;

import fr.eni.projet.eniencheres.bo.Enchere;

import java.util.List;

public interface EncheresRepository {
    List<Enchere> read();
    List<Enchere> read(String articleName, String category, boolean isAchat, int achatSelect, int venteSelect, String pseudo);
    Enchere find(long id);
    void create(long id_article, String acheteur, int montant);
    Enchere encherire(String pseudoAcheteur, long idArticle, int  montant);
    void updateStatusEnchere(long id, Number status);
    Enchere livrerEnchere(long id);
}
