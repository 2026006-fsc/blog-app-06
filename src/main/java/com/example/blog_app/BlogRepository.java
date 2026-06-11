package com.example.blog_app;

import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
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
    return jdbcClient.sql("SELECT id, author, title, content, created_at FROM blog WHERE id = :id")
        .param("id", id)
        .query(Blog.class)
        .optional();
  }

  public void save(Blog blog) {
    jdbcClient.sql(
        "INSERT INTO blog (id, author, title, content) VALUES (:author, :title, :content, NOW()")
        .param("title", blog.getTitle())
        .param("author", blog.getTitle())
        .param("content", blog.getContent())
        .update();

  }

}