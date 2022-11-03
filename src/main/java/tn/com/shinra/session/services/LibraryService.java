package tn.com.shinra.session.services;

import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    Library addLibrary(Library l);

    Optional<Library> findLibrary(long l);

    Library  updateLibrary(Library library);

    List<Library> getLibraries();

    public Library getLibraryById(long id);

    public Library getLibraryByName(String name);

    String deleteLibrary(long id);
}
