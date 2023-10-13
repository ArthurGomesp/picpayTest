package com.picpay.domain.transaction;

import com.picpay.domain.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "reciver_id")
    private User reciver;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Transaction(BigDecimal amount, User sender, User reciver) {
        this.amount = amount;
        this.sender = sender;
        this.reciver = reciver;
    }
}
