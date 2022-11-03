package tn.com.shinra.session.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Employee;
import tn.com.shinra.session.models.Library;
import tn.com.shinra.session.repositories.BookRepository;
import tn.com.shinra.session.repositories.LibraryRepository;
import tn.com.shinra.session.services.LibraryService;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public Library addLibrary(Library library) {
        return libraryRepository.save(library);

    }

    @Override
    public Optional<Library> findLibrary(long l) {
        return Optional.empty();
    }

    @Override
    public Library updateLibrary(Library library) {
        Library existingLibrary = libraryRepository.findById(library.getId()).orElse(null);
        existingLibrary.setName(library.getName());
        existingLibrary.setAdress(library.getAdress());
        return libraryRepository.save(existingLibrary);
    }

    @Override
    public List<Library> getLibraries() {
        return libraryRepository.findAll();
    }

    public Library getLibraryById(long id) {
        return libraryRepository.findById(id);
    }

    @Override
    public String deleteLibrary(long id) {
        libraryRepository.deleteById(id);
        return "library removed !! " + id;
    }
    @Override
    public Library getLibraryByName(String name) {
        return libraryRepository.findByName(name);
    }

}
