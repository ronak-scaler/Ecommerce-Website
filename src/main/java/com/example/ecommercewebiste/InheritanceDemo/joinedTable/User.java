package com.example.ecommercewebiste.InheritanceDemo.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import jakarta.persistence.Id;

@Entity(name="jt_user")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
