package com.zbank.credit_card_application.service;

import com.zbank.credit_card_application.dto.UserRequest;
import com.zbank.credit_card_application.dto.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);
}
