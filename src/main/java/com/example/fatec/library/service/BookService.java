package com.example.fatec.library.service;

import com.example.fatec.library.dto.BookDto;
import com.example.fatec.library.entity.Author;
import com.example.fatec.library.entity.Book;

import java.util.Collection;

public interface BookService {
    Book registerBook(BookDto bookDto);

    Collection<Book> getBooks();

    Book getOneBook(Long id) throws Exception;

    Book updateBook(Book book, BookDto bookDto);

    Book addAuthor(Book book, Author author);

    Book deleteBook(Long id);
}
