package com.example.authorbook.controller;

import com.example.authorbook.model.Author;
import com.example.authorbook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(value = "/authors")
    public String allAuthors(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "authors";
    }

    @GetMapping(value = "/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id){
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping(value = "/modifyAuthor")
    public String modifyAuthor(@RequestParam("id") int id, Model model){
        Author author = authorRepository.getOne(id);
        model.addAttribute("author", author);
        return "/modifyAuthor";
    }





 }
