package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.connectsphere.model.Post;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.PostRepository;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/home")
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam String content, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if (content.isEmpty() || content.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("postError", "Your post is empty!");
            return "redirect:/home";
        }
        LocalDateTime now = LocalDateTime.now();
        Post post = new Post(now, content, user);
        postRepository.save(post);
        return "redirect:/home";
    }

    @GetMapping("/post/{postId}")
    public String showPost(@PathVariable("postId") Long postId, Model model, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Post post = postRepository.findById(postId).orElse(null);
        User loggedInUser = (User) session.getAttribute("user");

        if (post == null) {
            throw new ChangeSetPersister.NotFoundException();
        }

        model.addAttribute("posts", post);
        model.addAttribute("post", post);
        model.addAttribute("loggedInUser", loggedInUser);

        return "post";
    }

    @PostMapping("/post/{postId}/deletePost")
    public String deletePost(@PathVariable("postId") Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return "redirect:/home";
        }
        postRepository.delete(post);
        return "redirect:/home";
    }
}
