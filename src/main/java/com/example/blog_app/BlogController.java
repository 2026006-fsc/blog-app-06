package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String getHome(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "blog";
    }

    @GetMapping("/post")
    public String postBlog() {
        return "post";
    }

    @GetMapping("/about")
    public String aboutBlog() {
        return "about";
    }

    @PostMapping("/books")
    public String create(@ModelAttribute BlogForm form) {
        blogService.register(form);
        return "redirect:/books";
    }

}
