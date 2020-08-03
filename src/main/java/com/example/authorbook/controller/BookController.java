package com.example.authorbook.controller;


import com.example.authorbook.model.Book;
import com.example.authorbook.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookService;

    @GetMapping(value = "/books")
    public String allBooks(ModelMap modelMap,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size){
        PageRequest pageRequest=PageRequest.of(page-1, size);
        Page<Book> books = bookService.findAll(pageRequest);
        int totalPages=books.getTotalPages();
        if (totalPages>0){
            List<Integer> pageNumbers= IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        modelMap.addAttribute("books", books);
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
