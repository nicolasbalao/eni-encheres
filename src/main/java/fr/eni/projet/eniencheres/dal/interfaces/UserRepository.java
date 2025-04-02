package fr.eni.projet.eniencheres.dal.interfaces;

import fr.eni.projet.eniencheres.bo.Utilisateur;

public interface UserRepository {
    public void save(Utilisateur user);

    public boolean existByPseudo(String pseudo);

    public boolean existByEmail(String email);
}
