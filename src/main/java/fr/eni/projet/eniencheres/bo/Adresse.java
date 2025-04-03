package fr.eni.projet.eniencheres.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Adresse {
    private Long id;
    @NotBlank
    @Size(min = 1, max = 100)
    private String rue;
    @NotBlank
    @Size(min = 3, max = 50)
    private String codePostal;
    @NotBlank
    @Size(min = 2, max = 10)
    private String ville;

    public Adresse() {
    }

    public Adresse(Long id, String rue, String codePostal, String ville) {
        this.id = id;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
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
        return rue + " " + codePostal + " " + ville;
    }
}
