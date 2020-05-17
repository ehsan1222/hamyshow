package ir.hamyiar.hamyshow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApplicationController {

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



}
