package com.appcent.todoist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private Boolean emailVerified;
    private Long identityNo;
    private List<CategoryResponseDto> category;
}
