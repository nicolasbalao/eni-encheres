package fr.eni.projet.eniencheres.bll.Encheres;

import fr.eni.projet.eniencheres.bo.Enchere;

import java.util.List;

public interface EncheresService {
    public List<Enchere> consulterEncheres();
    public List<Enchere> consulterEncheres(String articleName, String category, boolean isAchat, int achatSelect, int venteSelect, String pseudo);
    public Enchere consulterEnchere(long id);
}
