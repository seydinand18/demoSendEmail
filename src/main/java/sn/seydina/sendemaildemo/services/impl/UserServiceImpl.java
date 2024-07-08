package sn.seydina.sendemaildemo.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.seydina.sendemaildemo.exception.TokenExpiredException;
import sn.seydina.sendemaildemo.exception.UserAlreadyExistException;
import sn.seydina.sendemaildemo.models.Confirmation;
import sn.seydina.sendemaildemo.models.User;
import sn.seydina.sendemaildemo.repositories.ConfirmationRepository;
import sn.seydina.sendemaildemo.repositories.UserRepository;
import sn.seydina.sendemaildemo.services.EmailService;
import sn.seydina.sendemaildemo.services.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    @Override
    public User save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistException("User with email " + user.getEmail() + " already exists");
        }

       /* // Création de l'encodeur de mot de passe
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Hachage du mot de passe
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);*/

        user.setEnabled(false);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(1);

        Confirmation confirmation = Confirmation.builder()
                .token(token)
                .expiryDate(expiryDate)
                .user(user)
                .build();
        confirmationRepository.save(confirmation);

        emailService.sendSimpleEmailMessage(user.getName(), user.getEmail(), token);
        //emailService.sendMimeMessageWithAttachement(user.getName(), user.getEmail(), token);
        //emailService.sendMimeMessageWithEmbeddedFiles(user.getName(), user.getEmail(), token);
        //emailService.sendHtmlEmail(user.getName(), user.getEmail(), token);
        //emailService.sendHtmlEmailWithEmbeddedFiles(user.getName(), user.getEmail(), token);

        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        if (confirmation == null || confirmation.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("token invalide ou expiré");
        }

        // Token valide, activer l'utilisateur
        User user = confirmation.getUser();
        user.setEnabled(true);
        confirmationRepository.delete(confirmation);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        userRepository.deleteById(id);
    }
}
