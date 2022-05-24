package beans;
public class Utente {
    
    private String email;
    private Ruolo ruolo;

    public Utente() {}

    public Utente(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Ruolo getRuolo(){
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo){
        this.ruolo = ruolo;
    }

    public void blocca(){

    }

    public boolean isBloccato(){
        return false;
    }
}
