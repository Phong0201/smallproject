package com.smallproject.modules.users.dtos;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private String message;
    private Map<String, String> errors;

}
