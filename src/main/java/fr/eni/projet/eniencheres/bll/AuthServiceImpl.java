package fr.eni.projet.eniencheres.bll;

import fr.eni.projet.eniencheres.bll.interfaces.AuthService;
import fr.eni.projet.eniencheres.bo.User;
import fr.eni.projet.eniencheres.dal.interfaces.AddressRepository;
import fr.eni.projet.eniencheres.dal.interfaces.UserRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
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
    public User register(User user) throws BusinessException {
        // 1. Check if the pseudo already exist
        if (userRepository.existByPseudo(user.getPseudo())) {
            throw new BusinessException("Pseudo already exists");
        }
        // 2. Check if the email already exist
        if (userRepository.existByEmail(user.getEmail())) {
            throw new BusinessException("Email already exists");
        }
        // 3. Create password with encoder
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // 4. Save address
        addressRepository.save(user.getAddress());

        // 5. Save user
        userRepository.save(user);

        return null;
    }
}
