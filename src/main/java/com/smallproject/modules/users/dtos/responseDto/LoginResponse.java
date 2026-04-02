package com.smallproject.modules.users.dtos.responseDto;

import com.smallproject.modules.users.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginResponse {

    private String token;
    private UserDto user;


}
