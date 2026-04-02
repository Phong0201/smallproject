package com.smallproject.modules.users.dtos;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {

    private final Long id;
    private final String email;
    private final String name;

    public UserDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
