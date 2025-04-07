package fr.eni.projet.eniencheres.bo;

public enum StatutEnchere {
    NON_COMMENCEE(0, "Non commencée"),
    EN_COURS(1, "En cours"),
    CLOTUREE(2, "Clôturée"),
    LIVREE(3, "Livrée"),
    ANNULEE(4, "Annulée");

    private final int code;
    private final String description;

    StatutEnchere(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static StatutEnchere fromCode(int code) {
        for (StatutEnchere statut : values()) {
            if (statut.code == code) {
                return statut;
            }
        }
        throw new IllegalArgumentException("Statut inconnu : " + code);
    }
}
