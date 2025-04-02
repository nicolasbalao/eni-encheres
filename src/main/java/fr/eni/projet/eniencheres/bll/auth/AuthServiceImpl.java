package fr.eni.projet.eniencheres.bll.auth;

import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dal.adresse.AdresseRepository;
import fr.eni.projet.eniencheres.dal.utilisateur.UtilisateurRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import fr.eni.projet.eniencheres.validation.PasswordValidator;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UtilisateurRepository userRepository;
    private final AdresseRepository adresseRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(final UtilisateurRepository utilisateurRepository, final AdresseRepository adresseRepository) {
        this.userRepository = utilisateurRepository;
        this.adresseRepository = adresseRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    @Transactional
    @Override
    public void register(Utilisateur user) throws BusinessException {
        // 1. Check if the pseudo already exist
        if (userRepository.existByPseudo(user.getPseudo())) {
            throw new BusinessException("register.form.error.pseudoAlreadyExist");
        }
        // 2. Check if the email already exist
        if (userRepository.existByEmail(user.getEmail())) {
            throw new BusinessException("register.form.error.emailAlreadyExist");
        }

        if (!PasswordValidator.isValid(user.getMotDePasse())) {
            throw new BusinessException("register.form.error.passwordNotValid");
        }
        // Create password with encoder
        String hashedPassword = passwordEncoder.encode(user.getMotDePasse());
        user.setMotDePasse(hashedPassword);

        // Save address
        adresseRepository.save(user.getAdresse());

        // Save user
        userRepository.save(user);

    }
}
