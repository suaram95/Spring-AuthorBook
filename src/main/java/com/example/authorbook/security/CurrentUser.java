package com.example.authorbook.security;

import com.example.authorbook.model.Author;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    private Author author;


    public CurrentUser(Author author) {
        super(author.getUsername(), author.getPassword(), AuthorityUtils.createAuthorityList(author.getRole().name()));
    }

    public Author getAuthor() {
        return author;
    }
}
