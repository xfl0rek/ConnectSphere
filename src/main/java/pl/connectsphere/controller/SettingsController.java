package pl.connectsphere.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.connectsphere.dto.SettingsDTO;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.UserRepository;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final UserRepository userRepository;

    @Autowired
    public SettingsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showSettingsForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("settingsDTO", new SettingsDTO(loggedInUser));
        return "settings";
    }

    @PostMapping("/newSettings")
    public String saveSettings(@ModelAttribute("settingsDTO") SettingsDTO settingsDTO,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        User loggedInUser = (User) session.getAttribute("user");

        loggedInUser.setName(settingsDTO.getUsername());
        loggedInUser.setEmail(settingsDTO.getEmail());

        if (settingsDTO.getNewPassword() != null && !settingsDTO.getNewPassword().isEmpty() &&
                !settingsDTO.getNewPassword().equals(settingsDTO.getOldPassword())) {
            loggedInUser.setPassword(settingsDTO.getNewPassword());
        }

        userRepository.save(loggedInUser);

        redirectAttributes.addFlashAttribute("successMessage", "Settings updated successfully");
        return "redirect:/settings";
    }
}
