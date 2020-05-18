package ir.hamyiar.hamyshow.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserIn {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String mobile;
}
