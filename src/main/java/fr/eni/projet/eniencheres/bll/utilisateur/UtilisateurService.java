package fr.eni.projet.eniencheres.bll.utilisateur;

import fr.eni.projet.eniencheres.bo.Utilisateur;

public interface UtilisateurService {

    public Utilisateur displayProfile(String pseudo);

    public Utilisateur displayProfileDetails(String pseudo);

    public void updatePassword(String pseudo, String password, String newPassword);

    public void save(Utilisateur utilisateur);

    public void update(Utilisateur utilisateur);
}
