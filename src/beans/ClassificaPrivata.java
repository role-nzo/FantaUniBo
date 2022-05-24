package beans;

import java.util.*;

public class ClassificaPrivata extends Classifica{

    public ClassificaPrivata(String nome) {
        super(nome);
    }

    public Set<Giocatore> getGiocatori(){
        Set<Giocatore> giocatori = new HashSet<>();
        return giocatori;
    }
    
}
