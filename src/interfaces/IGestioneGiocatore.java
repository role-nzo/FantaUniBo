package interfaces;

import beans.Giocatore;

/**
 * IGestioneGiocatore
 */
public interface IGestioneGiocatore {

    public int getPunteggio(String email);  //l'email del giocatore di cui si vuole il punteggio viene presa dalla sessione

    public void modificaEmail(String oldEmail, String newEmail); //l'email del giocatore che si vuole modificare viene presa dalla sessione

    public Giocatore ottieniGiocatore(int id);

}