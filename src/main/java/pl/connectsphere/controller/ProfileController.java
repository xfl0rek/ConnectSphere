package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.PostRepository;

@Controller
public class ProfileController {
    private final PostRepository postRepository;

    public ProfileController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("posts", postRepository.findByUserId(loggedInUser.getId()));
        return "profile";
    }
}
