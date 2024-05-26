package pl.connectsphere.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import pl.connectsphere.model.Post;
import pl.connectsphere.model.User;
import pl.connectsphere.repository.PostRepository;
import pl.connectsphere.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public DBInit(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        postRepository.deleteAll();
        userRepository.deleteAll();

        userRepository.saveAll(List.of(
                new User("czarny", "czarny@dupa.pl", "123"),
                new User("1h4t3n1gg3rs", "debil123@niewiem.pl", "123")
        ));

        List<User> users = userRepository.findAll();

        postRepository.saveAll(List.of(
                new Post(LocalDateTime.now(), "Dupa.", users.get(0)),
                new Post(LocalDateTime.now(), "Witam.", users.get(1)),
                new Post(LocalDateTime.now(), "Yikes.", users.get(0))
        ));
    }
}
