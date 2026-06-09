package com.example.blog_app;

import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
  private final JdbcClient jdbcClient;

  public BlogRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Blog> findAll() {
    return jdbcClient.sql("SELECT id, title, content FROM blog")
        .query(Blog.class)
        .list();
  }

  public void save(String title, String content) {
    String sql = "INSERT INTO blog (title, content) VALUES (:title, :content)";

    jdbcClient.sql(sql)
        .param("title", title)
        .param("content", content)
        .update();
  }

}