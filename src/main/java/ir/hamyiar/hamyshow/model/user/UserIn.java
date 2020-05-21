package ir.hamyiar.hamyshow.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIn {
    private String username;
    private String password;
    private String passwordConfirm;
    private String fullName;
    private String email;
    private String mobile;
}
