package fr.eni.projet.eniencheres.bll;

import fr.eni.projet.eniencheres.bll.interfaces.AuthService;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dal.interfaces.AddressRepository;
import fr.eni.projet.eniencheres.dal.interfaces.UserRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import fr.eni.projet.eniencheres.validation.PasswordValidator;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(final UserRepository userRepository, final AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
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
        addressRepository.save(user.getAdresse());

        // Save user
        userRepository.save(user);

    }
}
