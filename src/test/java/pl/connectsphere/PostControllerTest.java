package pl.connectsphere;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.connectsphere.controller.PostController;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAddPost() throws Exception {
		// Wykonujemy żądanie POST na ścieżkę /home z danymi posta
		mockMvc.perform(post("/home")
						.param("createAt", String.valueOf(LocalTime.now()))
						.param("content", "dupa"))
				.andExpect(redirectedUrl("/home")); // Oczekujemy przekierowania na stronę /home
	}
}
