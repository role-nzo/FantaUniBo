package interfaces;

/**
 * ILogin
 */
public interface ILogin {

    public Ruolo verificaCredenziali(String email, String password);

    public boolean isUtenteBloccato(String email);

    public void bloccaUtente(String email);

}