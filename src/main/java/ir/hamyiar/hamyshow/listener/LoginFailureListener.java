package ir.hamyiar.hamyshow.listener;

import ir.hamyiar.hamyshow.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class LoginFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final UserService userService;

    public LoginFailureListener(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String username = String.valueOf(event.getAuthentication().getPrincipal());
        log.info("onApplicationEvent => " + username);
        userService.incorrectPassword(username);
    }
}
