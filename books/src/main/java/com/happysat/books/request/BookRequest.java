package com.happysat.books.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class BookRequest {

    @Size(min = 1, max = 30, message = "title length should be between 1-30")
    private String title;

    @Size(min = 1, max = 30, message = "author name length should be between 1-30")
    private String author;

    @Size(min = 1, max = 30, message = "category length should be between 1-30")
    private String category;

    @Min(value = 1, message = "minimum rating should be 1")
    @Max(value = 5, message = "maximum rating should be 5")
    private int rating;

    public BookRequest() {
    }

    public BookRequest(String title, String author, String category, int rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
