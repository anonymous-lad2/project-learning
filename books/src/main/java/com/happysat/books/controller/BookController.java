package com.happysat.books.controller;

import com.happysat.books.model.Book;
import com.happysat.books.request.BookRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated
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

    public Book convertToBook(long id, BookRequest bookRequest){
        return new Book(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRating()
        );
    }

//    @GetMapping("/api")
//    public String firstApi(){
//        return "Hello Guys!!";
//    }

    @GetMapping
    public List<Book> getBookByCategory(@Valid @RequestParam(required = false) String category){

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

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable @Min(1) long id){

//        for(Book book : books){
//            if(book.getTitle().equalsIgnoreCase(title)){
//                return book;
//            }
//        }
//        return null;

        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public void createBook(@Valid @RequestBody BookRequest bookRequest){

//        for(Book book : books){
//            if(book.getTitle().equalsIgnoreCase(newBook.getTitle())){
//                return;
//            }
//        }
//
//        books.add(newBook);

//        boolean isNewBook = books
//                .stream()
//                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));

//        if(isNewBook){
//            books.add(newBook);
//        }

        long id = books.isEmpty() ? 1 : books.getLast().getId() + 1;
        Book newbook = convertToBook(id, bookRequest);
        books.add(newbook);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable @Min(1) long id, @Valid @RequestBody BookRequest bookRequest){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                Book updatedBook = convertToBook(id, bookRequest);
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable @Min(1) long id){
        books.removeIf(book -> book.getId() == id);
    }

}
