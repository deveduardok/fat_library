package com.example.fatec.library.controller;

import com.example.fatec.library.dto.AuthorBookDto;
import com.example.fatec.library.dto.AuthorDto;
import com.example.fatec.library.entity.Author;
import com.example.fatec.library.exception.ResourceNotFoundException;
import com.example.fatec.library.service.AuthorServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorService;

    @GetMapping("")
    @JsonView(View.Author.class)
    public ResponseEntity<Collection<Author>> getAuthors() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.Author.class)
    public ResponseEntity<Author> getOneAuthor(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(authorService.getOneAuthor(id), HttpStatus.OK);
    }

    @PostMapping("")
    @JsonView(View.Author.class)
    public ResponseEntity<Author> postAuthor(@RequestBody AuthorDto authorDto, UriComponentsBuilder uriComponentsBuilder) {

        Author newAuthor = authorService.registerAuthor(authorDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/author/" + newAuthor.getId()).build().toUri());

        return new ResponseEntity<>(newAuthor, headers, HttpStatus.CREATED);
    }

    @PostMapping("/book")
    @JsonView(View.Author.class)
    public ResponseEntity<Author> postAuthorBook(@RequestBody AuthorBookDto authorBookDto, UriComponentsBuilder uriComponentsBuilder) {

        Author newAuthor = authorService.registerAuthorAndBook(authorBookDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/author/" + newAuthor.getId()).build().toUri());

        return new ResponseEntity<>(newAuthor, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @JsonView(View.Author.class)
    public ResponseEntity<Author> putAuthor(@RequestBody AuthorDto authorDto, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder) throws ResourceNotFoundException {
        Author author = authorService.getOneAuthor(id);
        Author updatedAuthor = authorService.updateAuthor(author, authorDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/author/" + updatedAuthor.getId()).build().toUri());

        return new ResponseEntity<>(updatedAuthor, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @JsonView(View.Author.class)
    public ResponseEntity<Author> deleteBook(@PathVariable Long id) {
        Author author = authorService.deleteAuthor(id);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

}
