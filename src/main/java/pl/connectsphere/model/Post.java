package pl.connectsphere.model;

import java.time.LocalDateTime;

public class Post {
    private LocalDateTime createdAt;
    private Long id;
    private String content;

    //No args constructor
    public Post() {
    }

    //All args constructor
    public Post(LocalDateTime createdAt, Long id, String content) {
        this.createdAt = createdAt;
        this.id = id;
        this.content = content;
    }

    //getters and setters
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
