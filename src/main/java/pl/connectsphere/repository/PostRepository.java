package pl.connectsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.connectsphere.model.Post;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userID);
}
