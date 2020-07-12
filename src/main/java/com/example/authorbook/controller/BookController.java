package com.example.authorbook.controller;


import com.example.authorbook.model.Author;
import com.example.authorbook.model.Book;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(value = "/books")
    public String allBooks(ModelMap modelMap){
        List<Book> all = bookRepository.findAll();
        modelMap.addAttribute("books", all);
        return "books";
    }


    @GetMapping(value = "/deleteBook")
    public String deleteBook(@RequestParam("id") int id){
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping(value="/modifyBook")
    public String modifyBook(@RequestParam("id") int id, Model model){
        Book book = bookRepository.getOne(id);
        model.addAttribute("book", book);
        return "/modifyBook";
    }

}
