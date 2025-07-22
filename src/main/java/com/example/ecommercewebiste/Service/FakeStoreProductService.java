package com.example.ecommercewebiste.Service;

import com.example.ecommercewebiste.Exceptions.ProductNotFound;
import com.example.ecommercewebiste.Models.Category;
import com.example.ecommercewebiste.Models.Product;
import com.example.ecommercewebiste.dtos.FakeStoreRequestDto;
import com.example.ecommercewebiste.dtos.FakeStoreResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFound {
        FakeStoreResponseDto fdto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreResponseDto.class);
        if(fdto == null){
            throw new ProductNotFound("Product not found");
        }
        return ConvertFakeStoreResponseDtoToProduct(fdto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponseDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreResponseDto[].class);
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
                .map(this::ConvertFakeStoreResponseDtoToProduct)
                .collect(Collectors.toList());
    }

    public Product ConvertFakeStoreResponseDtoToProduct(FakeStoreResponseDto fdto){
        Product p = new Product();
        p.setId(fdto.id);
        p.setDescription(fdto.desc);
        p.setTitle(fdto.title);
        p.setPrice(fdto.price);
        p.setImage(fdto.image);

        Category c = new Category();
        c.setId(fdto.id);
        c.setTitle(fdto.category);
        p.setCategory(c);
        return p;
    }

    public FakeStoreRequestDto ConvertProductToFakeStoreRequest(Product p){
        FakeStoreRequestDto requestDto = new FakeStoreRequestDto();
        requestDto.setCategory(p.getCategory().getTitle());
        requestDto.setDesc(p.getDescription());
        requestDto.setImage(p.getImage());
        requestDto.setPrice(p.getPrice());
        requestDto.setTitle(p.getTitle());
        return requestDto;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreRequestDto requestDto = ConvertProductToFakeStoreRequest(product);
        FakeStoreResponseDto response = restTemplate.postForObject("https://fakestoreapi.com/products", requestDto, FakeStoreResponseDto.class);
        if(response == null){
            throw new RuntimeException("Failed to create product");
        }
        return ConvertFakeStoreResponseDtoToProduct(response);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        FakeStoreRequestDto requestDto = ConvertProductToFakeStoreRequest(product);
        try{
            restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, requestDto, FakeStoreResponseDto.class);
        } catch (Exception e){
            System.out.println("Error occured");
        }
        return product;
    }

    @Override
    public Product deleteProduct(long id) {
        return null;
    }
}
