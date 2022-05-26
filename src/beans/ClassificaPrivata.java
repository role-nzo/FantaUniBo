package beans;

import java.util.Set;

import controller.ClassificheController;

public class ClassificaPrivata extends Classifica{

    private int id;
    private Set<Giocatore> giocatori = null;
    
    public ClassificaPrivata() {
    }

    public ClassificaPrivata(String nome) {
        super(nome);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Giocatore> getGiocatori(){
        if(giocatori == null) {
            giocatori = new ClassificheController().ottieniGiocatori(this.getId());
        }

        return giocatori;
    }

    public Set<Giocatore> setGiocatori(){
        return giocatori;
    }

}
