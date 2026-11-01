package com.zbank.credit_card_application.service;

import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.stereotype.Service;

import com.zbank.credit_card_application.dto.CreditCheckResponse;
import com.zbank.credit_card_application.entity.User;
import com.zbank.credit_card_application.exception.ResourceNotFoundException;
import com.zbank.credit_card_application.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service 
public class CreditCheckService {

    private final UserRepository userRepository;
    private final CreditBureauClient creditBureauClient;
    private final CreditScoreService creditScoreService;

    public CreditCheckService(
            UserRepository userRepository,
            CreditBureauClient creditBureauClient,
            CreditScoreService creditScoreService) {

        this.userRepository = userRepository;
        this.creditBureauClient = creditBureauClient;
        this.creditScoreService = creditScoreService;
    }

    @Transactional 
    public CreditCheckResponse checkCredit(String applicationNo) {

        Optional<User> userOptional =
        userRepository.findByApplicationNo(applicationNo);

if (userOptional.isEmpty()) {
    throw new ResourceNotFoundException(
            "Customer not found with application number: " + applicationNo
    );
}

User user = userOptional.get();

        OptionalInt bureauResult =
                creditBureauClient.getCreditScore(applicationNo);

        Integer bureauScore = bureauResult.isPresent()
                ? bureauResult.getAsInt()
                : null;

        int score = creditScoreService.calculateScore(
                bureauScore,
                user.getNoCreditCard(),
                user.getEmpSalary()
        );

        user.setCreditScore(score);

        User savedUser = userRepository.save(user);

        String scoreSource = bureauScore != null
                ? "CREDIT_BUREAU"
                : "INTERNAL_CALCULATION";

        return new CreditCheckResponse(
                savedUser.getApplicationNo(),
                savedUser.getFirstName() + " " + savedUser.getLastName(),
                savedUser.getCreditScore(),
                scoreSource,
                "Customer credit score updated successfully"
        );
    }
}
