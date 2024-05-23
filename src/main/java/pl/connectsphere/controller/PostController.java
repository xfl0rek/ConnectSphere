package pl.connectsphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.connectsphere.model.Post;

@Controller
@RequestMapping("/home")
public class PostController {
    @PostMapping
    private String addPost(Post post) {
        HomeController.posts.add(post);
        return "redirect:/home";
    }
}
