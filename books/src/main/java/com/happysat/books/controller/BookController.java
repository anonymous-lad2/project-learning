package com.happysat.books.controller;

import com.happysat.books.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController(){
        initializeBooks();
    }

    private void initializeBooks(){
        books.addAll(List.of(
                new Book("Title one", "Author one", "physics"),
                new Book("Title two", "Author two", "maths"),
                new Book("Title three", "Author three", "english"),
                new Book("Title four", "Author four", "chemistry"),
                new Book("Title five", "Author five", "java"),
                new Book("Title six", "Author six", "social studies")
        ));
    }

    @GetMapping("/api")
    public String firstApi(){
        return "Hello Guys!!";
    }

    @GetMapping("/api/books")
    public List<Book> getAllBooks(){
        return books;
    }
}
