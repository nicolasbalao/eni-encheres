package fr.eni.projet.eniencheres.dal.interfaces;

import fr.eni.projet.eniencheres.bo.User;

public interface UserRepository {
    public void save(User user);

    public boolean existByPseudo(String pseudo);

    public boolean existByEmail(String email);
}
