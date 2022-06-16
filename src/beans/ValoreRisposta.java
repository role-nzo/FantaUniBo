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
    
    public static String labelOf(int value) {
    	switch(value) {
    	case -3 : return "Si";
    	case -2 : return "No";
    	case -1 : return "Non so";
    	case 0 : return "Falso";
    	default : return String.valueOf(value);
    	}
    }
}
