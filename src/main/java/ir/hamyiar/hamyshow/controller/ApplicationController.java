package ir.hamyiar.hamyshow.controller;

import ir.hamyiar.hamyshow.exception.PasswordNotMatchException;
import ir.hamyiar.hamyshow.exception.UsernameAlreadyExistsException;
import ir.hamyiar.hamyshow.model.user.UserIn;
import ir.hamyiar.hamyshow.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/")
@Log4j2
public class ApplicationController {

    private final UserService userService;

    public ApplicationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String homePage() {
        return "home";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("accessdenied")
    public String accessDenied() {
        return "accessdenied";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserIn());
        return "register";
    }

    @PostMapping("register")
    public String submitRegister(@ModelAttribute("user") UserIn userIn) {

        try {
            log.info(userIn);
            userService.createUser(userIn);

        } catch (UsernameAlreadyExistsException e) {
            String encodeErrorMessage = URLEncoder.encode("username already exist", StandardCharsets.UTF_8);
            log.info(encodeErrorMessage);
            return "redirect:/register?error=" + encodeErrorMessage;
        } catch (PasswordNotMatchException e) {
            String encodedErrorMessage = URLEncoder.encode("password not match", StandardCharsets.UTF_8);
            log.info(encodedErrorMessage);
            return "redirect:/register?error=" + encodedErrorMessage;
        }

        return "redirect:/register";
    }


}
