package com.example.fatec.library.entity;


import com.example.fatec.library.controller.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({View.Book.class, View.Author.class})
    private long id;

    @Column(name = "title")
    @JsonView({View.Book.class, View.Author.class})
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
            joinColumns = { @JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    @JsonView(View.Book.class)
    private Set<Author> authorSet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editor_id")
    private Editor editor;

    //todo subjects

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<Author> authorSet) {
        this.authorSet = authorSet;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }
}
