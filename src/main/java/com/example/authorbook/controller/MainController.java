package com.example.authorbook.controller;

import com.example.authorbook.model.Author;
import com.example.authorbook.model.Book;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/")
    public String homePage(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "home";
    }

    @PostMapping("/saveAuthor")
    public String addUser(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }

    @PostMapping("/saveBook")
    public String addBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/";
    }






}
