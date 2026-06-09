package com.example.blog_app;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
  private final BlogRepository blogRepository;

  public BlogService(BlogRepository blogRepository) {
    this.blogRepository = blogRepository;
  }

  // 全件取得
  public List<Blog> findAll() {
    return blogRepository.findAll();
  }

  public void register(BlogForm form) {
    blogRepository.save(form.getTitle(), form.getContent());
  }
}
