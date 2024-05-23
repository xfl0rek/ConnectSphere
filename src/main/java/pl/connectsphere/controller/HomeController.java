package pl.connectsphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.connectsphere.model.Post;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    static final List<Post> posts = new ArrayList<>();

    static {
        posts.add(new Post(LocalTime.now(), 123L, "Dupa."));
        posts.add(new Post(LocalTime.now(), 164L, "Witam."));
        posts.add(new Post(LocalTime.now(), 574L, "Yikes."));
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", posts);
        return "home";
    }
}
