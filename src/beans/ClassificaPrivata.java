package beans;

import java.util.*;

public class ClassificaPrivata extends Classifica{

    Set<Giocatore> giocatori;
    
    public ClassificaPrivata() {}

    public ClassificaPrivata(String nome) {
        super(nome);
    }

    public Set<Giocatore> getGiocatori(){
        return giocatori;
    }
    
}
