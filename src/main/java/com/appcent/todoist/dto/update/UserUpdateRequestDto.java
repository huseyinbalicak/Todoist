package com.appcent.todoist.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {

    private Long id;
    @NotEmpty
    @Size(min = 3, max = 15)
    private String firstName;
    @NotEmpty
    @Size(min = 3, max = 15)
    private String lastName;
    @NotEmpty(message = "email should not be null or empty")
    @Email(message = "email should be a valid email format")
    @Size(min = 4, message = "Email must be at least 4 digits")
    private String email;
    @NotEmpty
    @Size(min = 6, max = 15, message = "Username must be at least 4 digits")
    private String userName;
    @NotEmpty
    @Size(min = 8)
    private String password;
    private List<CategoryUpdateRequestDto> category;
}
