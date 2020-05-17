package ir.hamyiar.hamyshow.model;

import java.sql.Timestamp;
import java.util.UUID;


public class Comment {
    private final UUID id;
    private final String message;
    private final Timestamp createDateTime;


    public Comment(String message, Timestamp createDateTime) {
        this.id = UUID.randomUUID();
        this.message = message;
        this.createDateTime = createDateTime;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }
}
