package com.happysat.books.controller;

import com.happysat.books.model.Book;
import org.springframework.web.bind.annotation.*;

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
                new Book("Title one", "Author one", "science"),
                new Book("Title two", "Author two", "maths"),
                new Book("Title three", "Author three", "english"),
                new Book("Title four", "Author four", "science"),
                new Book("Title five", "Author five", "java"),
                new Book("Title six", "Author six", "social studies")
        ));
    }

    @GetMapping("/api")
    public String firstApi(){
        return "Hello Guys!!";
    }

    @GetMapping("/api/books")
    public List<Book> getBookByCategory(@RequestParam(required = false) String category){

        if(category == null){
            return books;
        }
//        List<Book> filteredBooks = new ArrayList<>();
//        for(Book book : books){
//            if(book.getCategory().equalsIgnoreCase(category)){
//                filteredBooks.add(book);
//            }
//        }
//        return filteredBooks;

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title){

//        for(Book book : books){
//            if(book.getTitle().equalsIgnoreCase(title)){
//                return book;
//            }
//        }
//        return null;

        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }


}
