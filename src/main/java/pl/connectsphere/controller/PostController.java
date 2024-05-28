package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.connectsphere.model.Comment;
import pl.connectsphere.model.Post;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.CommentRepository;
import pl.connectsphere.repository.PostRepository;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/home")
public class PostController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostController(PostRepository postRepository,
                          CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam String content,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
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
    public String showPost(@PathVariable("postId") Long postId,
                           Model model,
                           HttpSession session)
            throws ChangeSetPersister.NotFoundException {
        Post post = postRepository.findById(postId).orElse(null);
        User loggedInUser = (User) session.getAttribute("user");

        if (post == null) {
            throw new ChangeSetPersister.NotFoundException();
        }

        model.addAttribute("posts", post);
        model.addAttribute("post", post);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("comments", commentRepository.findByPostId(postId));

        return "post";
    }

    @Transactional
    @PostMapping("/post/{postId}/deletePost")
    public String deletePost(@PathVariable("postId") Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return "redirect:/home";
        }
        commentRepository.deleteByPost(post);
        postRepository.delete(post);
        return "redirect:/home";
    }

    @PostMapping("/post/{postId}/addComment")
    public String addComment(@PathVariable("postId") Long postId,
                             @RequestParam String content,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return "redirect:/home";
        }
        if (user == null) {
            return "redirect:/";
        }
        if (content.isEmpty() || content.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("postError", "Your comment is empty!");
            return "redirect:/home/post/" + postId;
        }
        Comment comment = new Comment(content, post, user);
        commentRepository.save(comment);
        return "redirect:/home/post/" + postId;
    }
}
