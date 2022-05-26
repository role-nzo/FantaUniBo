package beans;

import java.util.*;

public class AzioneSignificativa {

    private int id;
    private int CFU;
    private String descrizione;
    private Optional<CorsoDiLaurea> corsoDiLaurea;

    public AzioneSignificativa() {}
    
    public AzioneSignificativa(int CFU, String descrizione) {
        this.CFU = CFU;
        this.descrizione = descrizione;
        this.corsoDiLaurea = Optional.empty();
    }

    public AzioneSignificativa(int CFU, CorsoDiLaurea corsoDiLaurea , String descrizione) {
        this.CFU = CFU;
        this.descrizione = descrizione;
        this.corsoDiLaurea = Optional.of(corsoDiLaurea);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Optional<CorsoDiLaurea> getCorsoDiLaurea(){
        return corsoDiLaurea;
    }

    public void setCorsoDiLaurea(Optional<CorsoDiLaurea> corsoDiLaurea){
        this.corsoDiLaurea = corsoDiLaurea;
    } 

    public static Set<AzioneSignificativa> getAllAzioniSignificative(){
        Set<AzioneSignificativa> azioniSignificative = new HashSet<>();
        return azioniSignificative;
    }
}
