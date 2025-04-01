package fr.eni.projet.eniencheres.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// TODO: make translation
public class User {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Le pseudo ne doit contenir que des caractères alphanumériques et des underscores.")
    @Size(min = 5, max = 30)
    private String pseudo;

    @NotBlank
    @Size(min = 1, max = 40)
    private String lastName;

    @NotBlank
    @Size(min = 1, max = 50)
    private String firstName;

    @NotBlank
    @Email
    private String email;

    private String phoneNumber;

    private String password;

    private int credits;
    private boolean admin;

    private Address address;

    public User() {

    }

    public User(String pseudo, String lastName, String firstName, String email, String phoneNumber, String password, Address address, int credits, boolean admin) {
        this.pseudo = pseudo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.credits = credits;
        this.admin = admin;
    }

    public User(String pseudo, String lastName, String firstName, String email, String phoneNumber, String password, Address address) {
        this.pseudo = pseudo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.credits = 10;
        this.admin = false;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
