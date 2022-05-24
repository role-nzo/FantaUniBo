package beans;

public enum ValoreRisposta {
    
    SI(-3, "Si"),
    NO(-2, "No"),
    NONSO(-1, "Non so"),
    FALSO(0, "Falso");

    public final int value;
    public final String label;

    private ValoreRisposta(int value, String label) {
        this.value = value;
        this.label = label;
    }
}
