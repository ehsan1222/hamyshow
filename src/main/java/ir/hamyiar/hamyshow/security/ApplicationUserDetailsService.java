package ir.hamyiar.hamyshow.security;

import ir.hamyiar.hamyshow.model.user.User;
import ir.hamyiar.hamyshow.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Service
@Log4j2
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public ApplicationUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByUsername(username);

        boolean isAccountNonBlock = true;
        if (user.getIncorrectPasswordFreezeAccount() != null && user.getIncorrectPasswordFreezeAccount().after(new Timestamp(System.currentTimeMillis()))) {
            isAccountNonBlock = false;
        }
        Set<SimpleGrantedAuthority> authorities = user.getUserInformation()
                .getApplicationUserRole()
                .getGrantedAuthority();
        
        return new ApplicationUserDetails(
                authorities,
                user.getUsername(),
                user.getPassword(),
                isAccountNonBlock,
                true
        );
    }
}
