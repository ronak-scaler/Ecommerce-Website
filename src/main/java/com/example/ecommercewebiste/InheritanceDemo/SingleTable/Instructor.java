package com.example.ecommercewebiste.InheritanceDemo.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name="St_instructor")

@DiscriminatorValue(value = "1")
public class Instructor extends User {
    private String specialization;
}