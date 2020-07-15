package com.example.authorbook.service;

import com.example.authorbook.model.Author;
import com.example.authorbook.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }

    public void deleteById(int id){
        authorRepository.deleteById(id);
    }

    public Author getAuthor(int id){
        return authorRepository.getOne(id);
    }

}
