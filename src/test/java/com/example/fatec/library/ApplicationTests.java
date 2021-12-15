package com.example.fatec.library;

import com.example.fatec.library.entity.Author;
import com.example.fatec.library.entity.Book;
import com.example.fatec.library.exception.ResourceNotFoundException;
import com.example.fatec.library.repository.AuthorRepository;
import com.example.fatec.library.repository.BookRepository;
import com.example.fatec.library.service.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;
import java.util.HashSet;

@SpringBootTest
@Transactional
@Rollback
class ApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorServiceImpl authorService;

	@Test
	void contextLoads() {
	}

	@Test
	void testInsert() {
		Book book = new Book();
		book.setTitle("Pai rico, pai pobre");

		bookRepository.save(book);

		Author author = new Author();
		author.setName("Robert T. Kiyosaki");
		author.setBookSet(new HashSet<>());
		author.getBookSet().add(book);

		authorRepository.save(author);

		book.setAuthorSet(new HashSet<>());
		book.getAuthorSet().add(author);

		bookRepository.save(book);

		assertEquals(book.getId(), author.getBookSet().iterator().next().getId());
		assertEquals(author.getId(), book.getAuthorSet().iterator().next().getId());
	}

	@Test
	void testFindBookByAuthorAndTitleContains() {
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			authorService.getOneAuthor(4L);
		});

		String expectedMessage = "Autor não existe";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
