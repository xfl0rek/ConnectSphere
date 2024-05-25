package pl.connectsphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.connectsphere.model.Post;
import pl.connectsphere.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    static final List<Post> posts = new ArrayList<>();

    static {
        posts.add(new Post(LocalDateTime.now(), "Dupa.", new User("czarny", "czarny@dupa.pl", "123")));
        posts.add(new Post(LocalDateTime.now(), "Witam.", new User("1h4t3n1gg3rs", "debil123@niewiem.pl", "123")));
        posts.add(new Post(LocalDateTime.now(), "Yikes.", new User("czarny", "czarny@dupa.pl", "123")));
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", posts);
        return "home";
    }
}
