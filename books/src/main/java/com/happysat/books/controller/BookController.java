package com.happysat.books.controller;

import com.happysat.books.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController(){
        initializeBooks();
    }

    private void initializeBooks(){
        books.addAll(List.of(
                new Book(1, "Title one", "Author one", "science", 3),
                new Book(2, "Title two", "Author two", "maths", 4),
                new Book(3, "Title three", "Author three", "english", 5),
                new Book(4, "Title four", "Author four", "science", 1),
                new Book(5, "Title five", "Author five", "java", 5),
                new Book(6, "Title six", "Author six", "social studies", 2)
        ));
    }

//    @GetMapping("/api")
//    public String firstApi(){
//        return "Hello Guys!!";
//    }

    @GetMapping
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

    @GetMapping("/{title}")
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

    @PostMapping
    public void createBook(@RequestBody Book newBook){

//        for(Book book : books){
//            if(book.getTitle().equalsIgnoreCase(newBook.getTitle())){
//                return;
//            }
//        }
//
//        books.add(newBook);

        boolean isNewBook = books
                .stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));

        if(isNewBook){
            books.add(newBook);
        }
    }

    @PutMapping("/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book updatedBook){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getTitle().equalsIgnoreCase(title)){
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable String title){
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

}
