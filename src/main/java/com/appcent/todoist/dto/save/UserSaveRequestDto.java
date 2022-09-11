package com.appcent.todoist.dto.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequestDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 15)
    private String userName;
    @NotNull
    @Size(min = 8)
    private String password;
    private Long identityNo;
    private List<CategorySaveRequestDto> category;
}
