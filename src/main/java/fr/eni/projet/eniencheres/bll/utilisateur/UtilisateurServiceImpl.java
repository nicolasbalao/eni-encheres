package fr.eni.projet.eniencheres.bll.utilisateur;

import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dal.utilisateur.UtilisateurRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(final UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur displayProfile(String pseudo) throws BusinessException {
        Utilisateur utilisateur = utilisateurRepository.profileByPseudo(pseudo);
        if (utilisateur == null) {
            throw new BusinessException("user.notFound");
        }
        return utilisateur;
    }
}
