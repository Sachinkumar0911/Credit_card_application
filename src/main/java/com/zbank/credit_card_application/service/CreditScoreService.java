package com.zbank.credit_card_application.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service 
public class CreditScoreService {

    private static final BigDecimal HIGH_SALARY =
            new BigDecimal("200000");

    private static final BigDecimal LOW_SALARY =
            new BigDecimal("50000");

    public int calculateScore(
            Integer bureauScore,
            Integer noCreditCard,
            BigDecimal annualSalary) {

        if (bureauScore != null) {
            return bureauScore;
        }

        if (noCreditCard != null && noCreditCard >= 2) {
            return 300;
        }

        if (annualSalary == null) {
            throw new IllegalArgumentException(
                    "Annual salary is required when bureau score is unavailable");
        }

        if (annualSalary.compareTo(HIGH_SALARY) > 0) {
            return 500;
        }

        if (annualSalary.compareTo(LOW_SALARY) > 0
                && annualSalary.compareTo(HIGH_SALARY) < 0) {
            return 150;
        }

        if (annualSalary.compareTo(LOW_SALARY) < 0) {
            return 50;
        }

        throw new IllegalArgumentException(
                "Salary does not match the configured credit scoring rules");
    }
}
