package com.zbank.credit_card_application.service;

import java.util.Locale;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zbank.credit_card_application.dto.UserRequest;
import com.zbank.credit_card_application.dto.UserResponse;
import com.zbank.credit_card_application.entity.User;
import com.zbank.credit_card_application.exception.ResourceConflictException;
import com.zbank.credit_card_application.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service 
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional 
    public UserResponse registerUser(UserRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        // Check duplicate email
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new ResourceConflictException(
                    "Email already registered");
        }

        // Generate unique application number
        String applicationNo;

        do {
            applicationNo = "ZBANK" +
                    UUID.randomUUID()
                            .toString()
                            .replace("-", "")
                            .substring(0, 10)
                            .toUpperCase(Locale.ROOT);

        } while (userRepository.existsByApplicationNo(applicationNo));

        // Create customer
        User user = new User();

        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setDob(request.getDob());

        // Hash application credential
        user.setDocNum(passwordEncoder.encode(request.getDocNum()));

        user.setApplicationNo(applicationNo);
        user.setEmail(email);
        //user.setNoCreditCard(0);
        user.setMobile(request.getMobile());
        user.setAddress(request.getAddress());
        user.setCurrencyCode(request.getCurrencyCode());
        user.setIsEmployed(request.getIsEmployed());
        user.setDocName(request.getDocName());
        //user.setDocNum(request.getDocNum());
        user.setEmpSalary(request.getEmpSalary());
        user.setNoCreditCard(request.getNoCreditCard());

        User savedUser = userRepository.save(user);

        // Never return PIN or identity document number
        return new UserResponse(
                 savedUser.getApplicationNo(),
                 "Customer registration successful. Application submitted."
         );
    }
}