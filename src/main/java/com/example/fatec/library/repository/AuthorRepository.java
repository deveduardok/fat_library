package com.example.fatec.library.repository;

import com.example.fatec.library.entity.Author;
import com.example.fatec.library.entity.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select au from Author au join Book b on au.id in b.authorSet join Editor p on b.editor = ?1 group by au.id")
    public List<Author> searchByEditor(Editor editor);
}
