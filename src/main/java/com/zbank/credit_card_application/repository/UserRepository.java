package com.zbank.credit_card_application.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zbank.credit_card_application.entity.User;
 
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByApplicationNo(String applicationNo);
    Optional<User> findByApplicationNo(String applicationNo);

}