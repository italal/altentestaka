package com.alten.ecomapp.back.service;

import com.alten.ecomapp.back.domain.User;
import com.alten.ecomapp.back.repository.UserRepository;
import com.alten.ecomapp.back.security.SecurityUtils;
import com.alten.ecomapp.back.service.dto.CompleteUserDTO;
import com.alten.ecomapp.back.service.dto.UserDTO;
import com.alten.ecomapp.back.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.random.RandomGenerator;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private final CacheManager cacheManager;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CacheManager cacheManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cacheManager = cacheManager;
    }


    public User registerUser(CompleteUserDTO userDTO, String password) {
        userRepository
                .findOneByUsername(userDTO.getUsername().toLowerCase())
                .ifPresent(existingUser -> {

                    throw new RuntimeException("username already used");

                });
        userRepository
                .findOneByEmailIgnoreCase(userDTO.getEmail())
                .ifPresent(existingUser -> {
                    throw new RuntimeException("email already used");

                });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(userDTO.getUsername().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }

        userRepository.save(newUser);
        //this.clearUserCaches(newUser);
        LOG.debug("Created Information for User: {}", newUser);
        return newUser;
    }


    public User createUser(CompleteUserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setFirstName(userDTO.getFirstName());
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail().toLowerCase());
        }

        String encryptedPassword = passwordEncoder.encode(RandomGenerator.getDefault().nextLong() + "");
        user.setPassword(encryptedPassword);

        userRepository.save(user);
        //this.clearUserCaches(user);
        LOG.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
    public Optional<CompleteUserDTO> updateUser(CompleteUserDTO userDTO) {
        return Optional.of(userRepository.findById(userDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    //this.clearUserCaches(user);
                    user.setUsername(userDTO.getUsername().toLowerCase());
                    user.setFirstName(userDTO.getFirstName());
                    if (userDTO.getEmail() != null) {
                        user.setEmail(userDTO.getEmail().toLowerCase());
                    }

                    userRepository.save(user);
                    //this.clearUserCaches(user);
                    LOG.debug("Changed Information for User: {}", user);
                    return user;
                })
                .map(CompleteUserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository
                .findOneByUsername(login)
                .ifPresent(user -> {
                    userRepository.delete(user);
                    //this.clearUserCaches(user);
                    LOG.debug("Deleted User: {}", user);
                });
    }


    public void updateUser(String firstName, String email) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByUsername)
                .ifPresent(user -> {
                    user.setFirstName(firstName);
                    if (email != null) {
                        user.setEmail(email.toLowerCase());
                    }

                    userRepository.save(user);
                    //this.clearUserCaches(user);
                    LOG.debug("Changed Information for User: {}", user);
                });
    }

    @Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByUsername)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPassword();
                    if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new RuntimeException("Invalid password");
                    }
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    //this.clearUserCaches(user);
                    LOG.debug("Changed password for User: {}", user);
                });
    }

    @Transactional(readOnly = true)
    public Page<CompleteUserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(CompleteUserDTO::new);
    }


    @Transactional(readOnly = true)
    public Optional<CompleteUserDTO> getCurrentUser() {
        return SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByUsername)
                .map(CompleteUserDTO::new);
    }

   /* @Transactional(readOnly = true)
    public User getCurrentUser(String login) {
        Optional<User> user=userRepository.findOneByUsername(login);
        UserMapper userMapper= new UserMapper();
        return userRepository.findByUser(user.get().getUsername().toString());
    }*/
    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getUsername());
        if (user.getEmail() != null) {
            Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
        }
    }
}
