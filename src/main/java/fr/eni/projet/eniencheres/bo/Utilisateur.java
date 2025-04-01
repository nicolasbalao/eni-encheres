package fr.eni.projet.eniencheres.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utilisateur {
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String motDePasse;
    private Number credit;
    private Boolean admin;
    private Adresse adresse;
    private List<ArticleAVendre> articleAVendres = new ArrayList<>();

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String motDePasse, Number credit, Boolean admin) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.admin = admin;
    }

    public Utilisateur() {
        this.pseudo = "";
        this.nom = "";
        this.prenom = "";
        this.email = "";
        this.telephone = "";
        this.motDePasse = "";
        this.credit = 0;
        this.admin = false;
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

    public void setCredit(Number credit) {
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

    public Number getCredit() {
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
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(getPseudo(), that.getPseudo()) && Objects.equals(getNom(), that.getNom()) && Objects.equals(getPrenom(), that.getPrenom()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getTelephone(), that.getTelephone()) && Objects.equals(getMotDePasse(), that.getMotDePasse()) && Objects.equals(getCredit(), that.getCredit()) && Objects.equals(getAdmin(), that.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPseudo(), getNom(), getPrenom(), getEmail(), getTelephone(), getMotDePasse(), getCredit(), getAdmin());
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
