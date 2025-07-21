package com.example.ecommercewebiste.Controller;

import com.example.ecommercewebiste.Exceptions.ProductNotFound;
import com.example.ecommercewebiste.Models.Product;
import com.example.ecommercewebiste.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService ps;


    public ProductController(ProductService productService){
        this.ps = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable long id) throws ProductNotFound {

        return new ResponseEntity<>(ps.getProductById(id), HttpStatus.OK);

    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return ps.getAllProducts();
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product){
        return ps.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return ps.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id){
        return;
    }
}
