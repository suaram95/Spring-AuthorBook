package com.example.authorbook.controller;

import com.example.authorbook.model.Author;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.service.AuthorService;
import com.example.authorbook.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AuthorController {


    @Value("${file.upload.dir}")
    private String uploadDir;
    private final AuthorService authorService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    //getMappingAuthors
    @GetMapping(value = "/authors")
    public String allAuthors(ModelMap modelMap) {
        modelMap.addAttribute("authors", authorService.findAll());
        return "authors";
    }

    //postMappingSaveAuthor
    @PostMapping("/saveAuthor")
    public String addUser(ModelMap map, @ModelAttribute Author author, @RequestParam("image") MultipartFile file, @RequestParam(name = "msg", required = false) String msg) throws IOException {
        if (!author.getPassword().equals(author.getConfirmPassword())) {
            return "redirect:/?msg=Password and Confirm password does not match!";
        }
        Optional<Author> byUsername = authorService.findByUsername(author.getUsername());
        if (byUsername.isPresent()) {
            return "redirect:/?msg=User already exists";
        }
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir, name);
        file.transferTo(image);
        author.setProfilePic(name);
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        author.setActive(false);
        author.setToken(UUID.randomUUID().toString());
        authorService.saveAuthor(author);
        String link = "http://localhost:8080/activate=?email=" + author.getUsername() + "&token=" + author.getToken();
        emailService.send(author.getUsername(),
                "Welcome", "Dear " + author.getName() +
                        " You have successfully registered.Please activate your account by clicking on " + link);
        return "redirect:/";
    }

    @GetMapping("/activate")
    public String activate(@RequestParam("email") String email, @RequestParam("token") String token) {
        Optional<Author> byUsername = authorService.findByUsername(email);
        if (byUsername.isPresent()) {
            Author author = byUsername.get();
            if (author.getToken().equals(token)) {
                author.setActive(true);
                author.setToken("");
                authorService.saveAuthor(author);
                return "redirect:/?msg=User was activated, please login!";
            }
        }
        return "redirect:/?msg=Something went wrong. Please try again";
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
