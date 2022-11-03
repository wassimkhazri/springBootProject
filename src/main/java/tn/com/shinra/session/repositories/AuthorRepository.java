package tn.com.shinra.session.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.com.shinra.session.models.AuthorModel;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
  // AuthorModel findById(long id);
    AuthorModel findByName(String name);
//    List<AuthorModel> findByNameContaining(String name);
    List<AuthorModel> findAuthorsByBooksId(Long bookId);
}
