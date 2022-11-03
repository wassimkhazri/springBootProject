package tn.com.shinra.session.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.shinra.session.models.Book;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
   Book findById(long Id);
    List<Book> findBooksByAuthorsId(Long authorId);
//
//    @Transactional
//    void deleteByAuthorModelId(long authorModelId);

}
