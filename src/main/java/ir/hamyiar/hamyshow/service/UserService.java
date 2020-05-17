package ir.hamyiar.hamyshow.service;

import ir.hamyiar.hamyshow.dao.UserInformationRepository;
import ir.hamyiar.hamyshow.dao.UserRepository;
import ir.hamyiar.hamyshow.exception.UsernameAlreadyExistsException;
import ir.hamyiar.hamyshow.exception.UsernameNotFoundException;
import ir.hamyiar.hamyshow.model.user.User;
import ir.hamyiar.hamyshow.model.user.UserInformation;
import ir.hamyiar.hamyshow.model.user.UserRegistryIn;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class UserService {
    public static final String USERNAME_NOT_FOUND_MESSAGE = "%s not found";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInformationRepository userInformationRepository;

    public UserService(UserRepository userRepository,
                       UserInformationRepository userInformationRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInformationRepository = userInformationRepository;
    }

    public void createUser(UserRegistryIn userRegistryIn) {
        if (userRepository.findByUsername(userRegistryIn.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(String.format("%s already exists.", userRegistryIn.getUsername()));
        }
        User user = new User(userRegistryIn.getUsername().trim(),
                passwordEncoder.encode(userRegistryIn.getPassword().trim()));

        UserInformation userInformation = new UserInformation(userRegistryIn.getFullName().trim(),
                userRegistryIn.getEmail().trim(),
                userRegistryIn.getMobile().trim());

        user.setUserInformation(userInformation);
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND_MESSAGE, username)));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsers(int offsetUser, int limitUser) {
        return userRepository.getAllUsers(offsetUser, limitUser);
    }


    public void changePassword(String username, String currentPassword, String newPassword) {
        Optional<User> optionalUser = isUserExists(username);
        optionalUser
                .filter(user ->
                        user.getPassword().equals(passwordEncoder.encode(currentPassword)))
                .ifPresentOrElse(user ->
                        user.setPassword(passwordEncoder.encode(newPassword)), UsernameNotFoundException::new);
    }

    // TODO: Update user will implement after design argument structure

    public void sendEmail(String username) {
        isUserExists(username)
                .filter(user -> !user.isValidateAccount())
                .ifPresent(
                        user -> {
                            UserInformation userInformation = user.getUserInformation();
                            // TODO: send email to email address
                            String generatedCode = UUID
                                    .randomUUID()
                                    .toString()
                                    .replace(".", "");
                            // send email

                            userInformation.setValidateCodeGenerated(generatedCode);
                            log.info("Email Generated code: " + generatedCode);
                            userInformationRepository.save(userInformation);
                        }
                );
    }


    public void validateEmail(String username, String generatedCode) {
        isUserExists(username)
                .ifPresent(
                        user -> {
                            UserInformation userInformation = user.getUserInformation();
                            if (userInformation
                                    .getValidateCodeGenerated()
                                    .equals(generatedCode)) {
                                userInformation.setValidatedEmail(true);
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                );
    }


    public void sendOtpCode(String username) {
        isUserExists(username)
                .ifPresent(
                        user -> {
                            UserInformation userInformation = user.getUserInformation();
                            int randomOtpCode = ThreadLocalRandom.current().nextInt(100000, 1000000);

                            //TODO: send sms to phone
                            log.info("randomOtpCode: " + randomOtpCode);

                            long expiredTimeTiMillis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2);
                            Timestamp otpExpired = new Timestamp(expiredTimeTiMillis);
                            userInformation.setOtpExpired(otpExpired);
                        }
                );
    }

    public void validatePhone (String username, String generatedOptCode) {
        isUserExists(username)
                .ifPresent(
                        user -> {
                            UserInformation userInformation = user.getUserInformation();
                            if (userInformation
                                    .getOtpCode()
                                    .equals(generatedOptCode)) {
                                userInformation.setValidatedPhone(true);
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                );
    }

    public void deleteUser(String username) {
        isUserExists(username);
        userRepository.deleteByUsername(username);
    }


    private Optional<User> isUserExists(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND_MESSAGE, username));
        }
        return optionalUser;
    }
}
