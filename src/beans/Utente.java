package beans;
public class Utente {
    String email;

    public Utente(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Ruolo getRuolo(){
        return null;
    }

    public void setRuolo(Ruolo ruolo){

    }

    public void blocca(){

    }

    public boolean isBloccato(){
        return false;
    }
}
