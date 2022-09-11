package com.appcent.todoist.security.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private Long identityNo;
    private String password;
}
