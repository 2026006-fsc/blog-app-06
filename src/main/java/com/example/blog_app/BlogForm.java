package com.example.blog_app;

public class BlogForm {
    private String title;
    private String content;

    // 引数なしのコンストラクタ（Spring Bootが自動で使うために必要）
    public BlogForm() {}

    // ゲッターとセッター
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}