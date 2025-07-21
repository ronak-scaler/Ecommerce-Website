package com.example.ecommercewebiste.InheritanceDemo.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity(name="St_mentor")

@DiscriminatorValue(value = "2")
public class Mentor extends User {

    private double mentorRating;

}