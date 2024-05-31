package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.UserRepository;
import pl.connectsphere.security.PasswordEncoder;

@Controller
@RequestMapping("/")
public class LoginController {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showLoginForm() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        passwordEncoder = new PasswordEncoder();
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.decrypt(user.getPassword()).equals(password)) {
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("message", "Invalid email or password.");
            return "redirect:/?error";
        }
    }
}