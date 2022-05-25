package beans;

import java.time.LocalDateTime;

public class Operazione extends Entry{
    
    private String operazione;

    public Operazione() {
        super();
    }

    public Operazione(LocalDateTime timestamp, String operazione) {
        super(timestamp);
        this.operazione = operazione;
    }

    public String getOperazione() {
        return operazione;
    }

    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }
    
}
