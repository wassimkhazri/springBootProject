package tn.com.shinra.session.controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.com.shinra.session.models.Author;
import tn.com.shinra.session.repositories.AuthRepository;
import tn.com.shinra.session.services.AuthService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthRepository authRepository;

    @Autowired
    AuthService authService;

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(required = false) String name) {
        List<Author> authors = new ArrayList<Author>();

        if (name == null)
            authRepository.findAll().forEach(authors::add);
        else
            authRepository.findByNameContaining(name).forEach(authors::add);

        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") long id) {
        Author author = authRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Author with id = " + id));

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        return authService.add_author(author);
    }
//    @PostMapping("/authors")
//    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
//        Author _author = authRepository.save(new Author(author.getName(), author.getAdress(), author.getPhonenumber()));
//        return new ResponseEntity<>(_author, HttpStatus.CREATED);
//    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") long id, @RequestBody Author author) {
        Author _author = authRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Author with id = " + id));

        _author.setName(author.getName());
        _author.setAdress(author.getAdress());
        _author.setPhonenumber(author.getPhonenumber());

        return new ResponseEntity<>(authRepository.save(_author), HttpStatus.OK);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") long id) {
        authRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/authors")
    public ResponseEntity<HttpStatus> deleteAllAuthors() {
        authRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
