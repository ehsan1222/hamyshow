package ir.hamyiar.hamyshow.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(columnDefinition = "serial")
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean validateAccount;
    private int numberOfIncorrectPassword;
    private Timestamp incorrectPasswordFreezeAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_information_id")
    private UserInformation userInformation;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.validateAccount = false;
        this.numberOfIncorrectPassword = 0;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }
}
