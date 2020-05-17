package ir.hamyiar.hamyshow.model.user;

import ir.hamyiar.hamyshow.security.auth.ApplicationUserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fullName;
    private String email;
    private String validateCodeGenerated;
    private boolean validatedEmail;
    private String mobile;
    private String otpCode;
    private Timestamp otpExpired;
    private boolean validatedPhone;
    private ApplicationUserRole applicationUserRole;

    @CreationTimestamp
    private Timestamp creationAccount;

    public UserInformation(String fullName, String email, String mobile) {
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.applicationUserRole = ApplicationUserRole.USER;
        this.validatedEmail = false;
        this.validatedPhone = false;
    }
}
