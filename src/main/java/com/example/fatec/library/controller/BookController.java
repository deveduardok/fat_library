package com.example.fatec.library.controller;

import com.example.fatec.library.dto.AddAuthorToBookDto;
import com.example.fatec.library.dto.AuthorBookDto;
import com.example.fatec.library.dto.BookDto;
import com.example.fatec.library.entity.Author;
import com.example.fatec.library.entity.Book;
import com.example.fatec.library.service.AuthorServiceImpl;
import com.example.fatec.library.service.BookServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorServiceImpl authorService;

    @GetMapping("")
    @JsonView(View.Book.class)
    public ResponseEntity<Collection<Book>> getBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.Book.class)
    public ResponseEntity<Book> getOneBook(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(bookService.getOneBook(id), HttpStatus.OK);
    }

    @PostMapping("")
    @JsonView(View.Book.class)
    public ResponseEntity<Book> postBook(@RequestBody BookDto bookDto, UriComponentsBuilder uriComponentsBuilder) {
        Book newBook = bookService.registerBook(bookDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/book/" + newBook.getId()).build().toUri());

        return new ResponseEntity<>(newBook, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @JsonView(View.Book.class)
    public ResponseEntity<Book> putBook(@RequestBody BookDto bookDto, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder) throws Exception {
        Book book = bookService.getOneBook(id);
        Book updatedBook = bookService.updateBook(book, bookDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/book/" + updatedBook.getId()).build().toUri());

        return new ResponseEntity<>(updatedBook, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @JsonView(View.Book.class)
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Book deletedBook = bookService.deleteBook(id);

        return new ResponseEntity<>(deletedBook, HttpStatus.OK);
    }

    public ResponseEntity<Book> addAuthor(@RequestBody AddAuthorToBookDto addAuthorToBookDto) throws Exception {
        Author author = authorService.getOneAuthor(addAuthorToBookDto.getAuthor_id());
        Book book = bookService.getOneBook(addAuthorToBookDto.getBook_id());

        bookService.addAuthor(book, author);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
