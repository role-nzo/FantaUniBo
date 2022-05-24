package beans;

import java.util.*;

public class Universita {
    
    public String descrizione;
    private String citta;

    public Universita(String descrizione, String citta) {
        this.descrizione = descrizione;
        this.citta = citta;
    }

    public String getDescrizione() {
        return descrizione;
    }



    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }



    public String getCitta() {
        return citta;
    }



    public void setCitta(String citta) {
        this.citta = citta;
    }



    public static Set<Universita> getAllUniversita(){
        Set<Universita> allUniversita = new HashSet<>();
        return allUniversita;
    } 

    public Set<CorsoDiLaurea> getCorsiDiLaurea(){
        Set<CorsoDiLaurea> corsiDiLaurea = new HashSet<>();
        return corsiDiLaurea;
    }

    public void setCorsiDiLaurea(Set<CorsoDiLaurea> corsiDiLaurea){

    }
}
