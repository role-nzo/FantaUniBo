package beans;

import java.util.*;
import java.util.regex.Pattern;

public class Giocatore extends Utente{

    private static final Pattern patternEmail = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    private int punteggio;
    private Set<Professore> commissione;
    private Set<Professore> professoriSeguiti;
    private CorsoDiLaurea corsoDiLaurea;
    private Set<Classifica> classifichePrivate = new HashSet<Classifica>();

    public Giocatore() {
        super();
    }

    // FIXME? - E' bella come soluzione? No, funziona? chapeau
    private static String validateEmail(String email){
        if( ! email.endsWith("unibo.it") || ! patternEmail.matcher(email).find() )
            throw new IllegalArgumentException();
        return email;
    }

    public Giocatore(String email){
        super(validateEmail(email));
    }

    @Override
    public void setEmail(String email){
        super.setEmail(validateEmail(email));
    }

    public int getPunteggio(){
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public Set<Professore> getProfessoriSeguiti() {
        return professoriSeguiti;
    }

    public void setProfessoriSeguiti(Set<Professore> professoriSeguiti) {
        this.professoriSeguiti = professoriSeguiti;
    }

    public Set<Professore> getCommissione() {
        return commissione;
    }

    public void setCommissione(Set<Professore> commissione) {
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

    public void abbandonaClassifica(Classifica classifica){
        classifichePrivate.remove(classifica);
    }
}
