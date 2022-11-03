package tn.com.shinra.session.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.shinra.session.models.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}
