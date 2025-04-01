package fr.eni.projet.eniencheres.bo;

import java.util.Objects;

public class CategorieBo {
    private Long id;
    private String libelle;


    public CategorieBo(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public CategorieBo() {
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
        CategorieBo that = (CategorieBo) o;
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
