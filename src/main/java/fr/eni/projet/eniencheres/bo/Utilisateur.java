package fr.eni.projet.eniencheres.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utilisateur {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "register.form.error.pseudo")
    @Size(min = 2, max = 30)
    private String pseudo;

    @NotBlank
    @Size(min = 1, max = 40)
    private String nom;

    @NotBlank
    @Size(min = 1, max = 50)
    private String prenom;

    @NotBlank
    @Email
    private String email;
    private String telephone;
    private String motDePasse;
    private int credit;
    private Boolean admin;
    private Adresse adresse;
    private List<ArticleAVendre> articleAVendres = new ArrayList<>();


    public Utilisateur() {
    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String motDePasse, int credit, Boolean admin) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.admin = admin;
    }


    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setArticleAVendres(List<ArticleAVendre> articleAVendres) {
        this.articleAVendres = articleAVendres;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public List<ArticleAVendre> getArticleAVendres() {
        return articleAVendres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(pseudo, that.pseudo) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, email);
    }

    @Override
    public String toString() {
        return "UtilisateurBo{" +
                "pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", credit=" + credit +
                ", admin=" + admin +
                ", adresse=" + adresse +
                ", articleAVendres=" + articleAVendres +
                '}';
    }
}
