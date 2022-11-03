package tn.com.shinra.session.controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.com.shinra.session.models.AuthorModel;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Tag;
import tn.com.shinra.session.repositories.AuthorRepository;
import tn.com.shinra.session.repositories.BookRepository;
import tn.com.shinra.session.services.BookService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookService.getBooks();
    }


    @GetMapping("/authors/{authorModelId}/books")
    public ResponseEntity<List<Book>> getAllBooksByAuthorId(@PathVariable(value = "authorModelId") Long authorModelId) {
        if (!authorRepository.existsById(authorModelId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + authorModelId);
        }

        List<Book> books = bookRepository.findBooksByAuthorsId(authorModelId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBooksById(@PathVariable(value = "id") Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Book with id = " + id));

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}/authors")
    public ResponseEntity<List<AuthorModel>> getAllAuthorsByBookId(@PathVariable(value = "bookId") Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Not found Book  with id = " + bookId);
        }

        List<AuthorModel> authors = authorRepository.findAuthorsByBooksId(bookId);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }


    @PostMapping("/authors/{authorModelId}/books")
    public ResponseEntity<Book> addBook(@PathVariable(value = "authorModelId") Long authorModelId, @RequestBody Book bookRequest) {
        Book book = authorRepository.findById(authorModelId).map(authorModel -> {
            long bookId;
            bookId = bookRequest.getId();

            // tag is existed
            if (bookId != 0L) {
                Book _book = bookRepository.findById(bookId);
                      //  .orElseThrow(() -> new ResourceNotFoundException("Not found Book with id = " + bookId));
                authorModel.addBook(_book);
                authorRepository.save(authorModel);
                return _book;
            }

            // add and create new Tag
            authorModel.addBook(bookRequest);
            return bookRepository.save(bookRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + authorModelId));

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }



    @DeleteMapping("/authors/{authorModelId}/books/{bookId}")
    public ResponseEntity<HttpStatus> deletebookFromAuthor(@PathVariable(value = "authorModelId") Long authorModelId, @PathVariable(value = "bookId") Long bookId) {
        AuthorModel authorModel = authorRepository.findById(authorModelId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Author with id = " + authorModelId));

        authorModel.removeBook(bookId);
        authorRepository.save(authorModel);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/bookById/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/bookByName/{name}")
    public Book findBookByName(@PathVariable String name) {
        return bookService.getBookByName(name);
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

//    @PutMapping("/books/{id}")
//    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
//        Optional<Book> bookData = Optional.ofNullable(bookRepository.findById(id));
//
//        if (bookData.isPresent()) {
//            Book _book = bookData.get();
//            _book.setName(book.getName());
//            _book.setPage(book.getPage());
//            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
