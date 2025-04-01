package fr.eni.projet.eniencheres.bo;

import java.time.LocalDate;
import java.util.Objects;

public class EnchereBo {
    private LocalDate date;
    private Number montant;
    private UtilisateurBo acquereur;
    private ArticleAVendreBo articleAVendre;

    public EnchereBo(LocalDate date, Number montant, UtilisateurBo acquereur, ArticleAVendreBo articleAVendre) {
        this.date = date;
        this.montant = montant;
        this.acquereur = acquereur;
        this.articleAVendre = articleAVendre;
    }

    public EnchereBo() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Number getMontant() {
        return montant;
    }

    public void setMontant(Number montant) {
        this.montant = montant;
    }

    public UtilisateurBo getAcquereur() {
        return acquereur;
    }

    public void setAcquereur(UtilisateurBo acquereur) {
        this.acquereur = acquereur;
    }

    public ArticleAVendreBo getArticleAVendre() {
        return articleAVendre;
    }

    public void setArticleAVendre(ArticleAVendreBo articleAVendre) {
        this.articleAVendre = articleAVendre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EnchereBo enchereBo = (EnchereBo) o;
        return Objects.equals(getDate(), enchereBo.getDate()) && Objects.equals(getMontant(), enchereBo.getMontant()) && Objects.equals(getAcquereur(), enchereBo.getAcquereur()) && Objects.equals(getArticleAVendre(), enchereBo.getArticleAVendre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getMontant(), getAcquereur(), getArticleAVendre());
    }

    @Override
    public String toString() {
        return "EnchereBo{" +
                "date=" + date +
                ", montant=" + montant +
                ", acquereur=" + acquereur +
                ", articleAVendre=" + articleAVendre +
                '}';
    }
}
