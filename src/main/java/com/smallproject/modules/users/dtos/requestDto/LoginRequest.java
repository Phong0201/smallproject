package com.smallproject.modules.users.dtos.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email không được bỏ trống!!")
    @Email(message = "Email khôg đúng định dạng!!")
    private String email;

    @Size(min = 12 , message = "mật khẩu ít nhất 12 ký tự")
    private String password;
}
