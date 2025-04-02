package fr.eni.projet.eniencheres.bo;

import java.util.Objects;

public class Categorie {
    private Long id;
    private String libelle;


    public Categorie(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Categorie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categorie that = (Categorie) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLibelle(), that.getLibelle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLibelle());
    }

    @Override
    public String toString() {
        return "CategorieBo{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
