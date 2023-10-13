package com.picpay.repositories;

import com.picpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface userRepository extends JpaRepository<User, Long> {
    Optional<User> findByDocument(String document);
    Optional<User> findById(Long id);

}
