package com.example.fatec.library.service;

import com.example.fatec.library.dto.BookDto;
import com.example.fatec.library.entity.Author;
import com.example.fatec.library.entity.Book;
import com.example.fatec.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Book registerBook(BookDto bookDto) {
        Book newBook = new Book();

        newBook.setTitle(bookDto.getTitle());
        bookRepository.save(newBook);

        return newBook;
    }

    @Override
    public Collection<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Book getOneBook(Long id) throws Exception {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new Exception("Livro não existe");
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Book updateBook(Book book, BookDto bookDto) {
        book.setTitle(bookDto.getTitle());

        bookRepository.save(book);

        return book;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Book addAuthor(Book book, Author author) {
        book.getAuthorSet().add(author);
        author.getBookSet().add(book);

        return book;
    }

    @Override
    public Book deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(value -> bookRepository.delete(value));

        return book.orElse(null);
    }
}
