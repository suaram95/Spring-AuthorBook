package com.example.authorbook.security;

import com.example.authorbook.model.Author;
import com.example.authorbook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthorService authorService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Author author = authorService.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Author not found"));
        return new CurrentUser(author);
    }
}
