package com.picpay.domain.user;

import com.picpay.dtos.userDataCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "users")

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    private com.picpay.domain.user.userType userType;

    public User(userDataCreateDto userData) {
        this.name = userData.name();
        this.lastName = userData.lastName();
        this.document = userData.document();
        this.email = userData.email();
        this.password = userData.password();
        this.balance = userData.balance();
        this.userType = userData.userType();
    }


}
