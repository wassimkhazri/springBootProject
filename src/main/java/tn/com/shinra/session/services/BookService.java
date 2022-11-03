package tn.com.shinra.session.services;

import tn.com.shinra.session.models.AuthorModel;
import tn.com.shinra.session.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book addBook(Book b);

    Optional<Book> findBook(long b);

    Book  updateBook(Book b);

    List<Book> getBooks();

    public Book getBookById(long id);

    public Book getBookByName(String name);

    String deleteBook(long id);
}
