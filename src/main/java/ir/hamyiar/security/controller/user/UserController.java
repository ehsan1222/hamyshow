package ir.hamyiar.security.controller.user;

import ir.hamyiar.security.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {

    @PostMapping
    public String createUser(@RequestBody User user) {

        return "redirect:/";
    }

    @GetMapping
    public String getUsers() {
        return "users";
    }


    @GetMapping(path = "{username}")
    public String getUserInformation(@PathVariable("username") String username) {
        User mockUser = new User(
                "Ehsan Maddahi",
                "ehsan",
                "password",
                "s.e.maddahi.ir@gmail.com",
                "0912000000"
        );
        return "user-details";
    }

    @PostMapping(path = "{username}")
    public String updateUserDetails(@PathVariable("username") String username,
                                  @RequestBody User user) {
        return "redirect:/users/" + username;
    }

    @PostMapping(path = "{username}/premium")
    public String upgradeToPremium(@PathVariable("username") String username) {

        return "redirect:/users/" + username;
    }

    @PostMapping(path = "{username}/admin")
    public String upgradeToAdmin(@PathVariable("username") String username) {

        return "redirect:/users";
    }

}
