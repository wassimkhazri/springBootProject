package tn.com.shinra.session.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.com.shinra.session.models.Author;

import java.util.List;

public interface AuthRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContaining(String name);

    List<Author> findAuthorsByTagsId(Long bookeId);
}
