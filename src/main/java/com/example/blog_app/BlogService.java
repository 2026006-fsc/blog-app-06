package com.example.blog_app;

import java.util.List;
import java.util.Optional;
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

    public void saveBlog(BlogForm blogForm) {
        if (blogForm == null) {
            //セーブしない
        }
        blogRepository.save(blogForm);
    }

    public Optional<Blog> findById(int id) {
        return blogRepository.searchById(id);
    }

}
