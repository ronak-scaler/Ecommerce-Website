package com.example.ecommercewebiste.InheritanceDemo.SingleTable;

import jakarta.persistence.*;
import lombok.Data;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@Entity(name="St_user")
@DiscriminatorColumn(
        name = "User_type",
        discriminatorType = DiscriminatorType.INTEGER
)

@DiscriminatorValue(value = "0")
public class User {
    @Id
    private long ID;
    private String name;
    private String email;

    private String password;
}