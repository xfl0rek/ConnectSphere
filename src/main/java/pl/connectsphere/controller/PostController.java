package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.connectsphere.model.Post;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.PostRepository;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if (content.isEmpty() || content.trim().isEmpty()) {
            return "redirect:/";
        }
        LocalDateTime now = LocalDateTime.now();
        Post post = new Post(now, content, user);
        postRepository.save(post);
        return "redirect:/";
    }
}
