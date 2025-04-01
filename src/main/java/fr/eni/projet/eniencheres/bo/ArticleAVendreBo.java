package fr.eni.projet.eniencheres.bo;

import java.time.LocalDate;
import java.util.Objects;

public class ArticleAVendreBo {
    private Long id;
    private String nom;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private Number statut;
    private Number prixInitial;
    private Number prixFinal;
    private UtilisateurBo vendeur;
    private AdresseBo retrait;
    private CategorieBo categorie;


    public ArticleAVendreBo(Long id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Number statut, Number prixInitial, Number prixFinal) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.statut = statut;
        this.prixInitial = prixInitial;
        this.prixFinal = prixFinal;
    }

    public ArticleAVendreBo() {
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

    public UtilisateurBo getVendeur() {
        return vendeur;
    }

    public void setVendeur(UtilisateurBo vendeur) {
        this.vendeur = vendeur;
    }

    public AdresseBo getRetrait() {
        return retrait;
    }

    public void setRetrait(AdresseBo retrait) {
        this.retrait = retrait;
    }

    public CategorieBo getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieBo categorie) {
        this.categorie = categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleAVendreBo that = (ArticleAVendreBo) o;
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
