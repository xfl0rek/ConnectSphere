package pl.connectsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.UserRepository;
import pl.connectsphere.security.PasswordEncoder;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes) {
        if (userRepository.existsByName(name)) {
            redirectAttributes.addFlashAttribute("message", "Username already exists!");
            return "redirect:/";
        }

        if (userRepository.existsByEmail(email)) {
            redirectAttributes.addFlashAttribute("message", "Email already in use!");
            return "redirect:/";
        }
        passwordEncoder = new PasswordEncoder();
        password = passwordEncoder.encrypt(password);
        User user = new User(name, email, password);
        userRepository.save(user);
        return "redirect:/";
    }
}