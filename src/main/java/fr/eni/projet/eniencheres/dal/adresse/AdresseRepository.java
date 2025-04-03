package fr.eni.projet.eniencheres.dal.adresse;

import fr.eni.projet.eniencheres.bo.Adresse;

public interface AdresseRepository {
    public void save(Adresse address);

    public int update(Adresse address);

    public Long findAdresseByID(Adresse address);

    public boolean isSharedAdresse(Long addressId, String pseudo);
}
