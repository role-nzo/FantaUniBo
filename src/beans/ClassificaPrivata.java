package beans;

import java.util.*;

public class ClassificaPrivata extends Classifica{

    Set<Giocatore> giocatori;
    
    public ClassificaPrivata() {
        this.giocatori = new HashSet<Giocatore>();
    }

    public ClassificaPrivata(String nome) {
        super(nome);
        this.giocatori = new HashSet<Giocatore>();
    }

    public Set<Giocatore> getGiocatori(){
        return giocatori;
    }

    public Set<Giocatore> setGiocatori(){
        return giocatori;
    }
    
}
