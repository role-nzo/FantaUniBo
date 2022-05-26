package beans;

import java.time.LocalDateTime;

public class Entry {
    
    private LocalDateTime timestamp;

    public Entry() {
    }

    public Entry(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    
    
}
