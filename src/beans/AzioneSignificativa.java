package beans;

import java.util.*;

public class AzioneSignificativa {

    private int CFU;
    private String descrizione;
    
    public AzioneSignificativa(int cFU, String descrizione) {
        CFU = cFU;
        this.descrizione = descrizione;
    }

    public int getCFU() {
        return CFU;
    }

    public void setCFU(int cFU) {
        CFU = cFU;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public CorsoDiLaurea getCorsoDiLaurea(){
        return null;
    }

    public void setCorsoDiLaurea(CorsoDiLaurea corsoDiLAurea){

    } 

    public static Set<AzioneSignificativa> getAllAzioniSignificative(){
        Set<AzioneSignificativa> azioniSignificative = new HashSet<>();
        return azioniSignificative;
    }

    

}
