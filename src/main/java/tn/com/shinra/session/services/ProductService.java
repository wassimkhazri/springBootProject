package tn.com.shinra.session.services;

import tn.com.shinra.session.models.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);
    List<Product> saveProducts(List<Product> products);

    List<Product> getProducts();

}
