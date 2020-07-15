package com.example.authorbook.controller;


import com.example.authorbook.model.Book;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;

    @GetMapping(value = "/books")
    public String allBooks(ModelMap modelMap){
        modelMap.addAttribute("books", bookService.findAll());
        return "books";
    }

    @PostMapping("/saveBook")
    public String addBook(@ModelAttribute Book book){
        bookService.saveBook(book);
        return "redirect:/";
    }

    @GetMapping(value = "/deleteBook")
    public String deleteBook(@RequestParam("id") int id){
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping(value="/modifyBook")
    public String modifyBook(@RequestParam("id") int id, Model model){
        model.addAttribute("book", bookService.getBook(id));
        return "/modifyBook";
    }

}
