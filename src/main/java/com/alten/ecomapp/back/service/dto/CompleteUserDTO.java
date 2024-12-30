package com.alten.ecomapp.back.service.dto;

import com.alten.ecomapp.back.config.Constants;
import com.alten.ecomapp.back.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * A DTO representing a user, with his authorities.
 */
public class CompleteUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.USERNAME_REGEX)
    @Size(min = 1, max = 50)
    private String username;

    @Size(max = 50)
    private String firstName;


    @Email
    @Size(min = 5, max = 254)
    private String email;


    public CompleteUserDTO() {
        // Empty constructor needed for Jackson.
    }

    public CompleteUserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // prettier-ignore
    @Override
    public String toString() {
        return "AdminUserDTO{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                "}";
    }
}
