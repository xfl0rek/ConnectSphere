package pl.connectsphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.connectsphere.model.Post;

import java.time.LocalTime;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("post", new Post(LocalTime.now(), 123L, "Dupa."));
        return "home";
    }
}
