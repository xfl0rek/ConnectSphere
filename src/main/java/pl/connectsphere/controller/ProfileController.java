package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.PostRepository;
import pl.connectsphere.repository.UserRepository;

@Controller
public class ProfileController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ProfileController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile/{userId}")
    public String profile(Model model, HttpSession session, @PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("posts", postRepository.findByUserId(user.getId()));
        model.addAttribute("loggedInUser", loggedInUser);
        return "profile";
    }
}
