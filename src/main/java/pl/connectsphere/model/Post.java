package pl.connectsphere.model;

import java.time.LocalTime;

public class Post {
    private LocalTime createdAt;
    private Long id;
    private String content;

    //No args constructor
    public Post() {
    }

    //All args constructor
    public Post(LocalTime createdAt, Long id, String content) {
        this.createdAt = createdAt;
        this.id = id;
        this.content = content;
    }

    //getters and setters
    public LocalTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setCreatedAt(LocalTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
