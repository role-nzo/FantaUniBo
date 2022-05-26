package beans;

import java.util.Set;

import controller.ClassificheController;

import java.util.HashSet;

public class ClassificaPrivata extends Classifica{

    Set<Giocatore> giocatori = null;
    
    public ClassificaPrivata() {
    }

    public ClassificaPrivata(String nome) {
        super(nome);
    }

    public Set<Giocatore> getGiocatori(){
        if(giocatori == null) {
            giocatori = new ClassificheController().ottieniGiocatori();
        }

        return giocatori;
    }

    public Set<Giocatore> setGiocatori(){
        return giocatori;
    }

}
