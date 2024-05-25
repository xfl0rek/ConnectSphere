package pl.connectsphere.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import pl.connectsphere.repository.UserRepository;

@Configuration
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;

    @Autowired
    public DBInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
