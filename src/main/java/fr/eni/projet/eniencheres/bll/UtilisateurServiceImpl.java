package fr.eni.projet.eniencheres.bll;

import fr.eni.projet.eniencheres.bll.interfaces.UtilisateurService;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dal.interfaces.UserRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UserRepository userRepository;

    public UtilisateurServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Utilisateur displayProfile(String pseudo) throws BusinessException {
        Utilisateur utilisateur = userRepository.profileByPseudo(pseudo);
        if (utilisateur == null) {
            throw new BusinessException("user.notFound");
        }
        return utilisateur;
    }
}
