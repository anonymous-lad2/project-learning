package com.happysat.books.controller;

import com.happysat.books.exception.BookErrorResponse;
import com.happysat.books.exception.BookNotFoundException;
import com.happysat.books.model.Book;
import com.happysat.books.request.BookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated
@Tag(name = "books", description = "Operations related to books")
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
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get all books", description = "Retrieve list of all available books")
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
    @ResponseStatus(HttpStatus.OK)
    @Parameter(description = "title of the book to retrieve")
    @Operation(summary = "get book by id", description = "Retrieve book using id")
    public Book getBookById(@PathVariable @Min(1) long id) throws BookNotFoundException {

//        for(Book book : books){
//            if(book.getTitle().equalsIgnoreCase(title)){
//                return book;
//            }
//        }
//        return null;

        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("book not found with id - " +id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create new book")
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable @Min(1) long id, @Valid @RequestBody BookRequest bookRequest) throws BookNotFoundException {
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                Book updatedBook = convertToBook(id, bookRequest);
                books.set(i, updatedBook);
                return;
            }
        }

        throw new BookNotFoundException("Book not found with id - " +id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a book by ID")
    public void deleteBook(@PathVariable @Min(1) long id) throws BookNotFoundException {
        boolean removed = books.removeIf(book -> book.getId() == id);

        if (!removed) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

}
