package beans;

public enum Ruolo {
    
    PARTECIPANTE, SUPERVISORE, AMMINISTRATORE;

    public static Ruolo from(String ruolo) {
        ruolo = ruolo.toLowerCase();

        switch(ruolo) {
            case "partecipante":
                return Ruolo.PARTECIPANTE;
            case "supervisore":
                return Ruolo.SUPERVISORE;
            case "amministratore":
                return Ruolo.AMMINISTRATORE;
            default:
                return null;
        }
    }

}
