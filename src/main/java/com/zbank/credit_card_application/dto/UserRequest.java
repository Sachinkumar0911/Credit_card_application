package com.zbank.credit_card_application.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
 
import jakarta.persistence.Column;
 import jakarta.validation.constraints.*;

public class UserRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "DOB is required")
    @Past(message = "DOB must be in the past")
    private LocalDate dob;

 

    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid email")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$",
             message = "Enter valid mobile number")
    private String mobile;

    private String address;

    @Pattern(regexp = "^[A-Z]{3}$",
             message = "Currency code must be 3 uppercase letters")
    private String currencyCode;

    @NotNull(message = "Employment status is required")
    private Boolean isEmployed;

    private String docName;
    private String docNum;

    
    @Column(name = "no_credit_card")
    private Integer noCreditCard = 0;
 
    public Integer getNoCreditCard() {
    return noCreditCard;
}

public void setNoCreditCard(Integer noCreditCard) {
    this.noCreditCard = noCreditCard;
}

    @DecimalMin(value = "0.0", inclusive = false,
                message = "Salary must be greater than zero")
    private BigDecimal empSalary;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
 
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

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
}