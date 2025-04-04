package fr.eni.projet.eniencheres.bo;

import fr.eni.projet.eniencheres.validation.articleAVendre.ArticleAVendreDateValidator;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@ArticleAVendreDateValidator
public class ArticleAVendre {
    private Long id;
    @Size(min = 2, max = 30)
    private String nom;
    @Size(min = 2, max = 300)
    private String description;

    @FutureOrPresent(message = "La date de début des enchères doit être dans le futur ou aujourd'hui")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebutEncheres;

    @Future(message = "La date de début des enchères doit être dans le futur ou aujourd'hui")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinEncheres;

    private Number statut;
    @Min(1)
    private Number prixInitial;
    private Number prixFinal;

    private Utilisateur vendeur;
    private Adresse retrait;
    private Categorie categorie;


    public ArticleAVendre(Long id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Number statut, Number prixInitial, Number prixFinal) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.statut = statut;
        this.prixInitial = prixInitial;
        this.prixFinal = prixFinal;
    }

    public ArticleAVendre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public Number getStatut() {
        return statut;
    }

    public void setStatut(Number statut) {
        this.statut = statut;
    }

    public Number getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(Number prixInitial) {
        this.prixInitial = prixInitial;
    }

    public Number getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(Number prixFinal) {
        this.prixFinal = prixFinal;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    public Adresse getRetrait() {
        return retrait;
    }

    public void setRetrait(Adresse retrait) {
        this.retrait = retrait;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleAVendre that = (ArticleAVendre) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNom(), that.getNom()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDateDebutEncheres(), that.getDateDebutEncheres()) && Objects.equals(getDateFinEncheres(), that.getDateFinEncheres()) && Objects.equals(getStatut(), that.getStatut()) && Objects.equals(getPrixInitial(), that.getPrixInitial()) && Objects.equals(getPrixFinal(), that.getPrixFinal()) && Objects.equals(getVendeur(), that.getVendeur()) && Objects.equals(getRetrait(), that.getRetrait()) && Objects.equals(getCategorie(), that.getCategorie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getDescription(), getDateDebutEncheres(), getDateFinEncheres(), getStatut(), getPrixInitial(), getPrixFinal(), getVendeur(), getRetrait(), getCategorie());
    }

    @Override
    public String toString() {
        return "ArticleAVendreBo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", statut=" + statut +
                ", prixInitial=" + prixInitial +
                ", prixFinal=" + prixFinal +
                ", vendeur=" + vendeur +
                ", retrait=" + retrait +
                ", categorie=" + categorie +
                '}';
    }
}
