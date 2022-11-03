package tn.com.shinra.session.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.shinra.session.models.AuthorModel;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.repositories.BookRepository;
import tn.com.shinra.session.services.BookService;

import java.util.List;
import java.util.Optional;

@Service
public  class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);

        // return "author added successfully";
    }

    @Override
    public Optional<Book> findBook(long b) {
        return Optional.empty();
    }


//    public Optional<Book> findbook(long b) {
//        return Optional.ofNullable(bookRepository.findById(b));
//    }


    @Override
    public Book updateBook(Book b) {
        return bookRepository.save(b);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }


    public Book getBookById(long id) {
        return bookRepository.findById(id);
    }
    @Override
    public String deleteBook(long id) {
        bookRepository.deleteById(id);
        return "book removed !! " + id;
    }
    @Override
    public Book getBookByName(String name) {
        return bookRepository.findByName(name);
    }

}
