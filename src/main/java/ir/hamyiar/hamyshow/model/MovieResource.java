package ir.hamyiar.hamyshow.model;

import java.sql.Timestamp;
import java.util.UUID;

public class MovieResource {

    private UUID id;
    private String path;
    private Timestamp createdDateTime;
    private Timestamp changeDateTime;


    public MovieResource(String path, Timestamp createdDateTime, Timestamp changeDateTime) {
        this.id = UUID.randomUUID();
        this.path = path;
        this.createdDateTime = createdDateTime;
        this.changeDateTime = changeDateTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Timestamp getChangeDateTime() {
        return changeDateTime;
    }

    public void setChangeDateTime(Timestamp changeDateTime) {
        this.changeDateTime = changeDateTime;
    }
}
