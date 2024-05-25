package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.connectsphere.model.Post;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    static final List<Post> posts = new ArrayList<>();

    static {
        posts.add(new Post(LocalDateTime.now(), "Dupa.", new User("czarny", "czarny@dupa.pl", "123")));
        posts.add(new Post(LocalDateTime.now(), "Witam.", new User("1h4t3n1gg3rs", "debil123@niewiem.pl", "123")));
        posts.add(new Post(LocalDateTime.now(), "Yikes.", new User("czarny", "czarny@dupa.pl", "123")));
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("posts", posts);
        return "home";
    }
}
