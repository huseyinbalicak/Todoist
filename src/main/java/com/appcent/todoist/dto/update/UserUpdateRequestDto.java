package com.appcent.todoist.dto.update;

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
public class UserUpdateRequestDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private List<CategoryUpdateRequestDto> category;
}
