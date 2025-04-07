package fr.eni.projet.eniencheres.bo;

import java.util.Objects;

public class Toast {
    public enum statut {
        SUCCESS,
        WARNING,
        DANGER
    }
    private statut statut;
    private String message;
    private String idToast;

    public Toast() {
        this.statut = statut.SUCCESS;
        this.message = "";
        this.idToast = "";
    }

    public Toast(statut statut, String message, String idToast) {
        this.statut = statut;
        this.message = message;
        this.idToast = idToast;
    }


    public statut getStatut() {
        return statut;
    }

    public void setStatut(statut statut) {
        this.statut = statut;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdToast() {
        return idToast;
    }

    public void setIdToast(String idToast) {
        this.idToast = idToast;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "statut=" + statut +
                ", message='" + message + '\'' +
                ", idToast='" + idToast + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Toast toast = (Toast) o;
        return getStatut() == toast.getStatut() && Objects.equals(getMessage(), toast.getMessage()) && Objects.equals(getIdToast(), toast.getIdToast());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatut(), getMessage(), getIdToast());
    }
}
