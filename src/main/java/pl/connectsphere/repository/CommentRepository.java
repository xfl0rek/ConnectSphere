package pl.connectsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.connectsphere.model.Comment;
import pl.connectsphere.model.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    void deleteByPost(Post post);
}
