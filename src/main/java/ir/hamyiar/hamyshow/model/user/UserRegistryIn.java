package ir.hamyiar.hamyshow.model.user;

import lombok.Data;

@Data
public class UserRegistryIn {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String mobile;
}
