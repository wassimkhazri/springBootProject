package tn.com.shinra.session.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Employee;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByName(String name);
    Employee findById(long id);
    List<Employee> findByLibraryContaining(String library);
    List<Employee> findByLibraryId(Long libraryId);
    @Transactional
    void deleteByLibraryId(long libraryId);
}
