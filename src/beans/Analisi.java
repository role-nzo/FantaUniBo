package beans;

import java.time.LocalDateTime;

public class Analisi extends Entry{

    private String messaggio;

    public Analisi(LocalDateTime timestamp, String messaggio) {
        super(timestamp);
        this.messaggio = messaggio;
    }

    public Analisi() {}

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    

    
}
