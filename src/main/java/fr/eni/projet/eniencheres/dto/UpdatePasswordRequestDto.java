package fr.eni.projet.eniencheres.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdatePasswordRequestDto {
    @NotBlank(message = "L'ancien mot de passe ne peut pas être vide")
    private String oldPassword;
    @NotBlank(message = "Le nouveau mot de passe ne peut pas être vide")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Le mot de passe doit contenir entre 8 et 20 caractères, au moins une majuscule, un chiffre et un caractère spécial"
    )
    private String newPassword;
    @NotBlank(message = "La confirmation du mot de passe ne peut pas être vide")
    private String confirmPassword;

    @NotBlank(message = "Le pseudo ne peut pas être vide")
    private String pseudo;

    @AssertTrue(message = "Les mots de passe ne correspondent pas")
    public boolean isPasswordMatching() {
        return newPassword != null && newPassword.equals(confirmPassword);
    }

    public UpdatePasswordRequestDto() {
    }

    public UpdatePasswordRequestDto(String oldPassword, String newPassword, String confirmPassword, String pseudo) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.pseudo = pseudo;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
