package com.example.authorbook.controller;

import com.example.authorbook.model.Author;
import com.example.authorbook.model.Book;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.repository.BookRepository;
import com.example.authorbook.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {


    private final AuthorService authorService;

    @GetMapping(value = "/")
    public String homePage(@AuthenticationPrincipal Principal principal, ModelMap modelMap) {
        modelMap.addAttribute("authors", authorService.findAll());
        return "home";
    }

    @GetMapping("/loginPage")
    public String loginpage(){
        return "loginPage";
    }




}
