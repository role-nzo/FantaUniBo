package interfaces;

/**
 * IGestioneGiocatore
 */
public interface IGestioneGiocatore {

    public int getPunteggio();  //l'email del giocatore di cui si vuole il punteggio viene presa dalla sessione

    public void modificaEmail(String newEmail); //l'email del giocatore che si vuole modificare viene presa dalla sessione

}