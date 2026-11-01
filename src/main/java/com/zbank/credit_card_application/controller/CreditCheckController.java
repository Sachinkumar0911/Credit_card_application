package com.zbank.credit_card_application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zbank.credit_card_application.dto.CreditCheckResponse;
import com.zbank.credit_card_application.service.CreditCheckService;

@RestController 
@RequestMapping ("/api/creditScorecheck")
public class CreditCheckController {

    private final CreditCheckService creditCheckService;

    public CreditCheckController(
            CreditCheckService creditCheckService) {
        this.creditCheckService = creditCheckService;
    }

    @PostMapping ("/{applicationNo}")
    public ResponseEntity<CreditCheckResponse> checkCredit(
            @PathVariable String applicationNo) {
System.out.println("applicationNo--"+applicationNo);
        CreditCheckResponse response =
                creditCheckService.checkCredit(applicationNo);

        return ResponseEntity.ok(response);
    }
}