package interfaces;

import java.time.LocalDateTime;
import beans.*;
import java.util.*;

/**
 * ILog
 */
public interface ILog {

    public List<Entry> getEntry();
    
    public List<String> analizza();

    public void aggiungiEntry(String testo, LocalDateTime ora);
    
}