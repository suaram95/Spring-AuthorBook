package com.example.authorbook.controller;

import com.example.authorbook.model.Author;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {


    @Value("${file.upload.dir}")
    private String uploadDir;
    private final AuthorService authorService;


    @GetMapping(value = "/authors")
    public String allAuthors(ModelMap modelMap) {
        modelMap.addAttribute("authors",  authorService.findAll());
        return "authors";
    }

    @PostMapping("/saveAuthor")
    public String addUser(@ModelAttribute Author author, @RequestParam("image") MultipartFile file) throws IOException {
        String name=System.currentTimeMillis()+"_"+file.getOriginalFilename();
        File image=new File(uploadDir,name);
        file.transferTo(image);
        author.setProfilePic(name);
        authorService.saveAuthor(author);
        return "redirect:/";
    }

    @GetMapping(value = "/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping(value = "/modifyAuthor")
    public String modifyAuthor(@RequestParam("id") int id, Model model) {
        model.addAttribute("author", authorService.getAuthor(id));
        return "/modifyAuthor";
    }

    @GetMapping(
            value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }


}
