package com.example.ecommercewebiste.Service;

import com.example.ecommercewebiste.Exceptions.ProductNotFound;
import com.example.ecommercewebiste.Models.Category;
import com.example.ecommercewebiste.Models.Product;
import com.example.ecommercewebiste.repo.CategoryRepo;
import com.example.ecommercewebiste.repo.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service("SelfProductService")
public class SelfProductService implements ProductService{

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFound {
        Optional<Product> optionalProduct = productRepo.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFound("Product not found");
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {

        // TODO: HANDLE EDGE CASES..
        // Before saving product we need to create category in db;

        Category category = product.getCategory();

        if(category.getId()==null){
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        } else {
            // we need to check if id exists..
            Optional<Category> optionalCategory = categoryRepo.findById(category.getId());
            if(optionalCategory.isEmpty()){
                throw new RuntimeException("Category is empty,.,");
            }
            product.setCategory(optionalCategory.get());
        }

        Product saveProduct = productRepo.save(product);

        Optional<Category> optionalCategory = categoryRepo.findById(category.getId());
        if(optionalCategory.isEmpty()){
            throw new RuntimeException("Category is empty,.,");
        }
        saveProduct.setCategory(optionalCategory.get());
        return saveProduct;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(long id) {
        return null;
    }
}
