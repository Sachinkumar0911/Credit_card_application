package com.zbank.credit_card_application.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private LocalDate dob;
 

    @Column(name = "application_no", nullable = false, unique = true)
    private String applicationNo;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "no_credit_card")
    private Integer noCreditCard = 0;

    private String mobile;
    private String address;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "is_employed")
    private Boolean isEmployed;

    @Column(name = "doc_name")
    private String docName;

    @Column(name = "doc_num")
    private String docNum;

    @Column(name = "emp_salary")
    private BigDecimal empSalary;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "credit_score")
private Integer creditScore;

public Integer getCreditScore() {
    return creditScore;
}

public void setCreditScore(Integer creditScore) {
    this.creditScore = creditScore;
}
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (noCreditCard == null) {
            noCreditCard = 0;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
 
    public String getApplicationNo() { return applicationNo; }
    public void setApplicationNo(String applicationNo) { this.applicationNo = applicationNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getNoCreditCard() { return noCreditCard; }
    public void setNoCreditCard(Integer noCreditCard) { this.noCreditCard = noCreditCard; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }

    public Boolean getIsEmployed() { return isEmployed; }
    public void setIsEmployed(Boolean isEmployed) { this.isEmployed = isEmployed; }

    public String getDocName() { return docName; }
    public void setDocName(String docName) { this.docName = docName; }

    public String getDocNum() { return docNum; }
    public void setDocNum(String docNum) { this.docNum = docNum; }

    public BigDecimal getEmpSalary() { return empSalary; }
    public void setEmpSalary(BigDecimal empSalary) { this.empSalary = empSalary; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}