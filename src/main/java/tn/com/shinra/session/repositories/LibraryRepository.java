package tn.com.shinra.session.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.shinra.session.models.Library;
@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {
    Library findById(long id);
    Library findByName(String name);
}
