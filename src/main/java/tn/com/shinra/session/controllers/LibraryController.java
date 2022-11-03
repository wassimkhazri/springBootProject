package tn.com.shinra.session.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Employee;
import tn.com.shinra.session.models.Library;
import tn.com.shinra.session.services.BookService;
import tn.com.shinra.session.services.LibraryService;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class LibraryController {
    @Autowired
    LibraryService libraryService;
    @PostMapping("/addLibrary")
    public Library addlibrary(@RequestBody Library library) {
        return libraryService.addLibrary(library);
    }

    @GetMapping("/libraries")
    public List<Library> findAllLibraries() {
        return libraryService.getLibraries();
    }

    @GetMapping("/libraries/{id}")
    public Library getLibrary(@PathVariable Long id) {
        return libraryService.getLibraryById(id);
    }

    @GetMapping("/libraryByName/{name}")
    public Library findLibraryByName(@PathVariable String name) {
        return libraryService.getLibraryByName(name);
    }

    @DeleteMapping("/libraries/{id}")
    public String deleteLibrary(@PathVariable Long id) {
        return libraryService.deleteLibrary(id);
    }

    @PutMapping("/libraries/{id}")
    public Library updateLibrary(@RequestBody Library library) {
        return libraryService.updateLibrary(library);
    }
}
