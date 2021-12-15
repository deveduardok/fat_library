package com.example.fatec.library.service;

import com.example.fatec.library.dto.AuthorBookDto;
import com.example.fatec.library.dto.AuthorDto;
import com.example.fatec.library.entity.Author;
import com.example.fatec.library.exception.ResourceNotFoundException;

import java.util.Collection;

public interface AuthorService {
    Author registerAuthor(AuthorDto authorDto);

    Collection<Author> getAuthors();

    Author getOneAuthor(Long id) throws ResourceNotFoundException;

    Author updateAuthor(Author author, AuthorDto authorDto);

    Author deleteAuthor(Long id);

    Author registerAuthorAndBook(AuthorBookDto authorBookDto);
}
