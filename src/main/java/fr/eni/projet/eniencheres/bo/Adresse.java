package fr.eni.projet.eniencheres.bo;

import java.util.Objects;

public class Adresse {
    private Long id;
    private String rue;
    private String codePostal;
    private String ville;

    public Adresse(Long id, String rue, String codePostal, String ville) {
        this.id = id;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Adresse() {
        this.id = 0L;
        this.rue = "";
        this.codePostal = "";
        this.ville = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresseBo = (Adresse) o;
        return Objects.equals(getId(), adresseBo.getId()) && Objects.equals(getRue(), adresseBo.getRue()) && Objects.equals(getCodePostal(), adresseBo.getCodePostal()) && Objects.equals(getVille(), adresseBo.getVille());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRue(), getCodePostal(), getVille());
    }

    @Override
    public String toString() {
        return "AdresseBo{" +
                "id=" + id +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
