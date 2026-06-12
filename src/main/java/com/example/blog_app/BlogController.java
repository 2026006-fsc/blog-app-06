package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // 一覧表示
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "home";
    }

    @GetMapping("/post")
    public String postForm() {
        return "post";
    }

    // @GetMapping("/post")
    // public String showForm(Model model) {
    // model.addAttribute(@ModelAttribute("blogForm") BlogForm blogForm);
    // return "post";
    // }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // 記事の個別閲覧
    @GetMapping("/blog/{id}")
    public String detail(@PathVariable int id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("blog", blogOpt.get());
        return "detail";
    }

    @GetMapping("/create/createNewBlog")
    public String createNewBlog(Model model) {
        model.addAttribute("blog", new Blog(0, "", "", ""));
        return "create/createNewBlog";
    }

    // フォームからの入力データをデータベースに保存する
    @PostMapping("/post")
    public String saveBlog(BlogForm blogForm) {
        blogService.saveBlog(blogForm);
        return "redirect:/";
    }

    // 投稿を保存したことを表示する処理
    // @GetMapping("create/complet")
    // public String complet() {
    // return "home";
    // }

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
