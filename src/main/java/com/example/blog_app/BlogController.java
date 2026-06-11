package com.example.blog_app;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {
    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("blogs", blogRepository.findAll());
        return "home";
    }

    @GetMapping("/post")
    public String showForm(Model model) {
        // もしクラス名が BlogForm なら、新しいインスタンスを作って渡す
        model.addAttribute("blogForm", new BlogForm()); 
        return "post";
    }
    
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        Optional<Blog> blogOpt = blogRepository.searchById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("blog", blogOpt.get());
        return "blog/detail";
    }

    @GetMapping("/create/createNewBlog")
    public String createNewBlog(Model model) {
        model.addAttribute("blog", new Blog(0, "", "", ""));
        return "create/createNewBlog";
    }

    @PostMapping("/create/save")
    public String saveBlog(@ModelAttribute Blog blog) {
        blogRepository.save(blog);
        return "create/complet";
    }

}
// public class BlogController {
// private final BlogService blogService;

// public BlogController(BlogService blogService) {
// this.blogService = blogService;
// }

// @GetMapping("/")
// public String home() {
// return "home";
// }

// @GetMapping("blog")
// public String getBlog() {
// return "blog";
// }

// @GetMapping("/post")
// public String postBlog() {
// return "post";
// }

// @GetMapping("/about")
// public String about() {
// return "about";
// }

// @PostMapping("/blog")
// public String create(@ModelAttribute BlogForm form) {
// blogService.register(form);
// return "redirect:/blog";
// }

// }
