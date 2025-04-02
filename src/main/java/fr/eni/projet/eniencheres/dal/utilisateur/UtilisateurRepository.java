package fr.eni.projet.eniencheres.dal.utilisateur;

import fr.eni.projet.eniencheres.bo.Utilisateur;

public interface UtilisateurRepository {
    public void save(Utilisateur user);

    public boolean existByPseudo(String pseudo);

    public boolean existByEmail(String email);

    public Utilisateur profileByPseudo(String pseudo);

    public Utilisateur profileDetailsByPseudo(String pseudo);
}
