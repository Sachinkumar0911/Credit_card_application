package com.zbank.credit_card_application.service;

import java.util.OptionalInt;

public interface CreditBureauClient {

    OptionalInt getCreditScore(String applicationNo);
}