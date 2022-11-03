package tn.com.shinra.session.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.com.shinra.session.models.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findTagsByAuthorsId(Long authorId);
}
