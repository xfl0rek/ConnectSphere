package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.PostRepository;
import pl.connectsphere.repository.UserRepository;

@Controller
public class HomeController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public HomeController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("posts", postRepository.findAll());
        return "home";
    }
}
