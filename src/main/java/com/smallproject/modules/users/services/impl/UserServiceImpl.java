package com.smallproject.modules.users.services.impl;

import com.smallproject.modules.users.dtos.ErrorDto;
import com.smallproject.modules.users.dtos.UserDto;
import com.smallproject.modules.users.dtos.requestDto.LoginRequest;
import com.smallproject.modules.users.dtos.responseDto.LoginResponse;
import com.smallproject.modules.users.entitys.User;
import com.smallproject.modules.users.repository.UserRepository;
import com.smallproject.services.BaseService;
import com.smallproject.modules.users.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl extends BaseService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Object authenticate(LoginRequest request) {

        try {

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Email hoặc mật khẩu không chính xác "));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Email hoặc mật khẩu không chính xác ");
            }

            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();
            String token = jwtService.generateToken(user.getId(), user.getEmail());

            return new LoginResponse(token, userDto);

        } catch (BadCredentialsException e) {
            logger.error("lỗi xác thực {}", e.getMessage());

            Map<String, String> errors = new HashMap<>();
            errors.put("message", e.getMessage());
            ErrorDto errorDto = new ErrorDto(
                    "có vấn đề sảy ra trong quá trình xác thực " , errors);

            return errorDto;
        }
    }
}
