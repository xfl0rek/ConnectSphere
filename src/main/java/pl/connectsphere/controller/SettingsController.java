package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.connectsphere.dto.SettingsDTO;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.UserRepository;
import pl.connectsphere.security.PasswordEncoder;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SettingsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showSettingsForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("settingsDTO", new SettingsDTO(loggedInUser));
        model.addAttribute("loggedInUser", loggedInUser);
        return "settings";
    }

    @PostMapping("/newSettings")
    public String saveSettings(@ModelAttribute("settingsDTO") SettingsDTO settingsDTO,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        User loggedInUser = (User) session.getAttribute("user");

        if (!loggedInUser.getName().equals(settingsDTO.getUsername()) &&
                userRepository.existsByName(settingsDTO.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Username already exists");
            return "redirect:/settings";
        }

        if (!loggedInUser.getEmail().equals(settingsDTO.getEmail()) &&
                userRepository.existsByEmail(settingsDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "Email already exists");
            return "redirect:/settings";
        }

        loggedInUser.setName(settingsDTO.getUsername());
        loggedInUser.setEmail(settingsDTO.getEmail());

        passwordEncoder = new PasswordEncoder();

        if (loggedInUser.getPassword().equals(passwordEncoder.encrypt(settingsDTO.getOldPassword())) &&
                settingsDTO.getNewPassword() != null && !settingsDTO.getNewPassword().isEmpty() &&
                !settingsDTO.getNewPassword().equals(settingsDTO.getOldPassword())) {
            loggedInUser.setPassword(passwordEncoder.encrypt(settingsDTO.getNewPassword()));
        }

        userRepository.save(loggedInUser);

        redirectAttributes.addFlashAttribute("successMessage", "Settings updated successfully");
        return "redirect:/settings";
    }
}
