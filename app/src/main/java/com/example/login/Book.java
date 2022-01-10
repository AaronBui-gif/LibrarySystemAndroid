package com.example.login;

public class Book {
    private String name;
    private String category;
    private String author;
    private int img;

    public Book(String name, String category, String author, int img) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
