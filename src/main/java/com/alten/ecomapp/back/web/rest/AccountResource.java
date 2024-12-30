package com.alten.ecomapp.back.web.rest;

import com.alten.ecomapp.back.domain.User;
import com.alten.ecomapp.back.repository.UserRepository;
import com.alten.ecomapp.back.security.SecurityUtils;
import com.alten.ecomapp.back.service.UserService;
import com.alten.ecomapp.back.service.dto.CompleteUserDTO;
import com.alten.ecomapp.back.service.dto.PasswordChangeDTO;
import com.alten.ecomapp.back.web.rest.errors.*;
import com.alten.ecomapp.back.web.rest.vm.KeyAndPasswordVM;
import com.alten.ecomapp.back.web.rest.vm.ManagedUserVM;
import jakarta.validation.Valid;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private static class AccountResourceException extends RuntimeException {

        private AccountResourceException(String message) {
            super(message);
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(AccountResource.class);

    private final UserRepository userRepository;

    private final UserService userService;


    public AccountResource(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param managedUserVM the managed user View Model.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
     */
    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
    }



    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    public CompleteUserDTO getAccount() {
        return SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByUsername)
            .map(CompleteUserDTO::new)
            .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }

    /**
     * {@code POST  /account} : update the current user information.
     *
     * @param userDTO the current user information.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user login wasn't found.
     */
   /* @PostMapping("/account")
    public void saveAccount(@Valid @RequestBody CompleteUserDTO userDTO) {
        String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new AccountResourceException("Current user login not found"));
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.orElseThrow().getUsername().equalsIgnoreCase(userLogin))) {
            throw new AccountResourceException("email already exist");
        }
        Optional<User> user = userRepository.findOneByUsername(userLogin);
        if (!user.isPresent()) {
            throw new AccountResourceException("User could not be found");
        }
        userService.updateUser(
            userDTO.getFirstName(),
            userDTO.getEmail()
        );
    }

*/


    private static boolean isPasswordLengthInvalid(String password) {
        return (
            StringUtils.isEmpty(password) ||
            password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
            password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }
}
