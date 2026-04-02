package com.smallproject.modules.users.services;

import com.smallproject.modules.users.dtos.requestDto.LoginRequest;

public interface UserService {
    Object authenticate(LoginRequest request);
}
