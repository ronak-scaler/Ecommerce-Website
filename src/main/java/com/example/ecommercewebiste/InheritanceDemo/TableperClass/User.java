package com.example.ecommercewebiste.InheritanceDemo.TableperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;


@Entity(name="TPC_User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class User {
    @Id
    private long ID;
    private String name;
    private String email;

    private String password;
}