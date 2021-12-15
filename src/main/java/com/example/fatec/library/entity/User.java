package com.example.fatec.library.entity;

import com.example.fatec.library.controller.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.User.class)
    private long id;

    @Column(name = "name")
    @JsonView({View.User.class})
    private String name;

    @Column(name = "email")
    @JsonView({View.User.class})
    private String email;

    @Column(name = "password")
    @JsonView({View.User.class})
    private String password;

    @JsonView(View.User.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorizations",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "auth_id") }
    )
    private Set<Authorization> authorizationSet;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authorization> getAuthorizationSet() {
        return authorizationSet;
    }

    public void setAuthorizationSet(Set<Authorization> authorizationSet) {
        this.authorizationSet = authorizationSet;
    }
}
