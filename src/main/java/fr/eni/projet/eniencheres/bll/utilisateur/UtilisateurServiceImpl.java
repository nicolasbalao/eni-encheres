package fr.eni.projet.eniencheres.bll.utilisateur;

import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dal.adresse.AdresseRepositoryImpl;
import fr.eni.projet.eniencheres.dal.utilisateur.UtilisateurRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final AdresseRepositoryImpl adresseRepositoryImpl;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(final UtilisateurRepository utilisateurRepository, AdresseRepositoryImpl adresseRepositoryImpl) {
        this.utilisateurRepository = utilisateurRepository;
        this.adresseRepositoryImpl = adresseRepositoryImpl;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public Utilisateur displayProfile(String pseudo) throws BusinessException {
        Utilisateur utilisateur = utilisateurRepository.profileByPseudo(pseudo);
        if (utilisateur == null) {
            throw new BusinessException("error.user.notFound");
        }
        return utilisateur;
    }

    @Override
    public Utilisateur displayProfileDetails(String pseudo) {
        Utilisateur utilisateur = utilisateurRepository.profileDetailsByPseudo(pseudo);
        if (utilisateur == null) {
            throw new BusinessException("error.user.notFound");
        }
        return utilisateur;
    }

    @Transactional
    @Override
    public void updateProfile(Utilisateur utilisateur) {

        // 1. Update profile
        int userRow = utilisateurRepository.update(utilisateur);
        if (userRow == 0) {
            throw new BusinessException("error.user.notFound");
        }
        // 2. Update address
        int addressRow = adresseRepositoryImpl.update(utilisateur.getAdresse());
        if (addressRow == 0) {
            throw new BusinessException("error.address.notFound");
        }

    }

    @Override
    public void updatePassword(String pseudo, String password, String newPassword) {
        String storedHashedPassword = utilisateurRepository.getPassword(pseudo);

        if (!passwordEncoder.matches(password, storedHashedPassword)) {
            throw new BusinessException("error.user.passwordMismatch");
        }

        String newPasswordHash = passwordEncoder.encode(newPassword);

        utilisateurRepository.updatePassword(pseudo, newPasswordHash);
    }
}
