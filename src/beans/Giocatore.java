package beans;

import java.util.*;
import java.util.regex.Pattern;

public class Giocatore extends Utente {

    private static final Pattern patternEmail = Pattern.compile(
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    private int punteggio;
    private Set<Professore> commissione;
    private Set<Professore> professoriSeguiti;
    private CorsoDiLaurea corsoDiLaurea;
    private Set<Classifica> classifichePrivate;

    public Giocatore() {
        super();
        classifichePrivate = new HashSet<Classifica>();
        professoriSeguiti = new HashSet<Professore>();
        commissione = new HashSet<Professore>();
    }

    // FIXME? - E' bella come soluzione? No, funziona? chapeau
    private static String validateEmail(String email) {
        if (!email.endsWith("unibo.it") || !patternEmail.matcher(email).find())
            throw new IllegalArgumentException();
        return email;
    }

    public Giocatore(String email) {
        super(validateEmail(email));
        classifichePrivate = new HashSet<Classifica>();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(validateEmail(email));
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) { // CHANGE - METTERE?
        this.punteggio = punteggio;
    }

    public Set<Professore> getProfessoriSeguiti() {
        return professoriSeguiti;
    }

    public void setProfessoriSeguiti(Set<Professore> professoriSeguiti) {
        if (professoriSeguiti.size() < 3 || professoriSeguiti.stream().anyMatch(p -> ! p.getCorsiDiLaurea().contains(this.corsoDiLaurea)) )
            throw new IllegalArgumentException();
        this.professoriSeguiti = professoriSeguiti;
    }

    public Set<Professore> getCommissione() {
        return commissione;
    }

    public void setCommissione(Set<Professore> commissione) {
        if (commissione.size() != 4 || commissione.stream().anyMatch(p -> ! p.getCorsiDiLaurea().contains(this.corsoDiLaurea)) )
            throw new IllegalArgumentException();
        this.commissione = commissione;
    }

    public CorsoDiLaurea getCorsoDiLaurea() {
        return corsoDiLaurea;
    }

    public void setCorsoDiLaurea(CorsoDiLaurea corsoDiLaurea) {
        this.corsoDiLaurea = corsoDiLaurea;
    }

    public Set<Classifica> getClassifichePrivate() {
        return classifichePrivate;
    }

    public void partecipaClassifica(Classifica classifica) {
        classifichePrivate.add(classifica);
    }

    public void abbandonaClassifica(Classifica classifica) {
        classifichePrivate.remove(classifica);
    }
}
