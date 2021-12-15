package com.example.fatec.library.entity;

import com.example.fatec.library.controller.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({View.Book.class, View.Author.class})
    private long id;

    @Column(name = "name")
    @JsonView({View.Book.class, View.Author.class})
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authorSet")
    @JsonView(View.Author.class)
    private Set<Book> bookSet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }
}
