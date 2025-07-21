package com.example.ecommercewebiste.InheritanceDemo.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name="St_ta")

@DiscriminatorValue(value = "3")
public class TA extends User {

    private int noOfSession;
    private double rating;
}