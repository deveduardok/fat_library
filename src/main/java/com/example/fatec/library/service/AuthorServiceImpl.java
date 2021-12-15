package com.example.fatec.library.service;

import com.example.fatec.library.dto.AuthorBookDto;
import com.example.fatec.library.dto.AuthorDto;
import com.example.fatec.library.dto.BookDto;
import com.example.fatec.library.entity.Author;
import com.example.fatec.library.entity.Book;
import com.example.fatec.library.exception.ResourceNotFoundException;
import com.example.fatec.library.repository.AuthorRepository;
import com.example.fatec.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Author registerAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());

        return author;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Author registerAuthorAndBook(AuthorBookDto authorBookDto) {
        Author author = new Author();
        Book book = new Book();

        author.setName(authorBookDto.getName());
        book.setTitle(authorBookDto.getTitle());

        book.setAuthorSet(new HashSet<>());
        book.getAuthorSet().add(author);

        author.setBookSet(new HashSet<>());
        author.getBookSet().add(book);

        authorRepository.save(author);
        bookRepository.save(book);

        return author;
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getOneAuthor(Long id) throws ResourceNotFoundException {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        }
        throw new ResourceNotFoundException("Autor não existe");
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Author updateAuthor(Author author, AuthorDto authorDto) {
        author.setName(authorDto.getName());

        authorRepository.save(author);
        return author;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Author deleteAuthor(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        author.ifPresent(value -> authorRepository.delete(value));

        return author.orElse(null);
    }
}
