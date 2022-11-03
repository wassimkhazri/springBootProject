package tn.com.shinra.session.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.com.shinra.session.models.AuthorModel;
import tn.com.shinra.session.repositories.AuthorRepository;
import tn.com.shinra.session.services.AuthorService;
import tn.com.shinra.session.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")

@RestController
@RequestMapping("/api")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookService bookService;


	@PostMapping("/addAuthor")
	public AuthorModel addAuthor(@RequestBody AuthorModel authorModel) {
		return authorService.add_author(authorModel);
	}

	@GetMapping("/authorss")
        public List<AuthorModel> findAllAuthors() {
		return authorService.getAuthors();
	}

	@GetMapping("/authorss/{id}")
	public AuthorModel getAuthor(@PathVariable Long id) {
		return authorService.getAuthorById(id);
	}

	@GetMapping("/authorByName/{name}")
	public AuthorModel findAuthorByName(@PathVariable String name) {
		return authorService.getAuthorByName(name);
	}

	@DeleteMapping("/authorss/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);
    }

//	@PutMapping("/authors/{id}")
//	public AuthorModel updateAuthor(@RequestBody AuthorModel authorModel) {
//		return authorService.updateAuthor(authorModel);
//	}


	@DeleteMapping("/authorss")
	public ResponseEntity<HttpStatus> deleteAllAuthors() {
		try {
			authorRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}











//	@GetMapping("/authors")
//	public ResponseEntity<List<AuthorModel>> getAllAuthors(@RequestParam(required = false) String name) {
//		try {
//			List<AuthorModel> authors = new ArrayList<AuthorModel>();
//
//			if (name == null)
//				authorRepository.findAll().forEach(authors::add);
//			else
//				authorRepository.findByNameContaining(name).forEach(authors::add);
//
//			if (authors.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(authors, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}