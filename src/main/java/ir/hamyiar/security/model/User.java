package ir.hamyiar.security.model;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class User {

    private final String username;
    private final String password;
    private final String fullName;
    private final String email;
    private final String mobileNumber;
    private final Timestamp creationTime;

    public User(String fullName, String username, String password, String email, String mobileNumber) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
        this.creationTime = new Timestamp(System.currentTimeMillis());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }
}
