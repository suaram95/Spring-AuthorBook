package com.example.authorbook.security;

import com.example.authorbook.model.Author;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    private Author author;

    public CurrentUser(Author author) {
        super(author.getUsername(), author.getPassword(),author.isActive(),true,true,true, AuthorityUtils.createAuthorityList(author.getRole().name()));
        this.author=author;
    }

    public Author getAuthor() {
        return author;
    }
}
