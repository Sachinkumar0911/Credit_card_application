package com.zbank.credit_card_application.service;

import java.util.OptionalInt;

import org.springframework.stereotype.Component;

@Component 
public class MockCreditBureauClient implements CreditBureauClient {

    @Override
    public OptionalInt getCreditScore(String applicationNo) {

        return OptionalInt.empty();
    }
}