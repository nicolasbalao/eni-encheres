package fr.eni.projet.eniencheres.dal.adresse;

import fr.eni.projet.eniencheres.bo.Adresse;

import java.util.List;

public interface AdresseRepository {
    public void save(Adresse address);

    public int update(Adresse address);

    public Long findAdresseByAdresse(Adresse address);

    public Adresse findAdresseById(Long id);

    public boolean isSharedAdresse(Long addressId, String pseudo);

    public List<Adresse> findEniAdresse();

    public Adresse findAdresseByUtilisateurPseudo(String pseudo);

}
