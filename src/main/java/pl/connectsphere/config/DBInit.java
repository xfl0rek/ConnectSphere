package pl.connectsphere.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import pl.connectsphere.model.Post;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.CommentRepository;
import pl.connectsphere.repository.PostRepository;
import pl.connectsphere.repository.UserRepository;
import pl.connectsphere.security.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DBInit(UserRepository userRepository,
                  PostRepository postRepository,
                  CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        commentRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();

        passwordEncoder = new PasswordEncoder();

        userRepository.saveAll(List.of(
                new User("user1", "dupa@dupa.pl", passwordEncoder.encrypt("123")),
                new User("user2", "dupa@niewiem.pl", passwordEncoder.encrypt("123"))
        ));

        List<User> users = userRepository.findAll();

        postRepository.saveAll(List.of(
                new Post(LocalDateTime.now(), "Dupa.", users.get(0)),
                new Post(LocalDateTime.now(), "Witam.", users.get(1)),
                new Post(LocalDateTime.now(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua..", users.get(0))
        ));
    }
}
