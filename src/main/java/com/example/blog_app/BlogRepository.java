package com.example.blog_app;

import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Repository
public class BlogRepository {
  private final JdbcClient jdbcClient;

  public BlogRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Blog> findAll() {
    return jdbcClient.sql("SELECT id,  author, title, content FROM blog")
        .query(Blog.class)
        .list();
  }

  public Optional searchById(int id) {
    return jdbcClient.sql("SELECT id, author, title, content FROM blog WHERE id = :id")
        .param("id", id)
        .query(Blog.class)
        .optional();
  }

  public void save(BlogForm blogForm) {
    jdbcClient.sql(
        "INSERT INTO blog_app.blog (author, title, content) VALUES (:author, :title, :content)")
        .param("title", blogForm.getTitle())
        .param("author", blogForm.getAuthor())
        .param("content", blogForm.getContent())
        .update();

  }
}