package com.example.fatec.library.entity;

import com.example.fatec.library.controller.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorizations")
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.Authorization.class)
    private long id;

    @JsonView({View.User.class, View.Authorization.class})
    @Column(name = "name")
    private String name;

    @JsonView(View.Authorization.class)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authorizationSet")
    private Set<User> userSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
