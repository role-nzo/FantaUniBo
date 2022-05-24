package beans;

import java.util.*;

public class AzioneSignificativa {

    private int CFU;
    private String descrizione;
    private CorsoDiLaurea corsoDiLaurea;

    public AzioneSignificativa() {}
    
    public AzioneSignificativa(int CFU, String descrizione) {
        this.CFU = CFU;
        this.descrizione = descrizione;
    }

    public int getCFU() {
        return CFU;
    }

    public void setCFU(int CFU) {
        this.CFU = CFU;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public CorsoDiLaurea getCorsoDiLaurea(){
        return corsoDiLaurea;
    }

    public void setCorsoDiLaurea(CorsoDiLaurea corsoDiLAurea){
        this.corsoDiLaurea = corsoDiLAurea;
    } 

    public static Set<AzioneSignificativa> getAllAzioniSignificative(){
        Set<AzioneSignificativa> azioniSignificative = new HashSet<>();
        return azioniSignificative;
    }

    

}
