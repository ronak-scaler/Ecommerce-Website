package com.example.ecommercewebiste.Service;

import com.example.ecommercewebiste.Exceptions.ProductNotFound;
import com.example.ecommercewebiste.Models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(long id) throws ProductNotFound;

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(long id, Product product);

    Product deleteProduct(long id);
}
