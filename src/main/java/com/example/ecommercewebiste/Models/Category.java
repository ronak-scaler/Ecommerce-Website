package com.example.ecommercewebiste.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseModel{

    private String title;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;
}