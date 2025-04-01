package fr.eni.projet.eniencheres.bo;

import java.time.LocalDate;
import java.util.Objects;

public class Enchere {
    private LocalDate date;
    private Number montant;
    private Utilisateur acquereur;
    private ArticleAVendre articleAVendre;

    public Enchere(LocalDate date, Number montant, ArticleAVendre articleAVendre) {
        this.date = date;
        this.montant = montant;
        this.articleAVendre = articleAVendre;
    }

    public Enchere() {
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

    public Utilisateur getAcquereur() {
        return acquereur;
    }

    public void setAcquereur(Utilisateur acquereur) {
        this.acquereur = acquereur;
    }

    public ArticleAVendre getArticleAVendre() {
        return articleAVendre;
    }

    public void setArticleAVendre(ArticleAVendre articleAVendre) {
        this.articleAVendre = articleAVendre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enchere enchereBo = (Enchere) o;
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
