package beans;

import java.util.ArrayList;
import java.util.List;

public class Log {

    private List<Entry> entries;

    public Log(List<String> entries) {
        this.entries = new ArrayList<Entry>();
    }

    public List<Entry> getEntries() {
        return entries;
    }
    
    public List<Entry> analizza(){
        return null;
    }
    
    
}
