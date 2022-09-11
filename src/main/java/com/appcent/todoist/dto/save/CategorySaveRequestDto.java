package com.appcent.todoist.dto.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySaveRequestDto {

    @Size(min = 5)
    private String description;
    private UserSaveRequestDto user;
    private List<TodoSaveRequestDto> todoList;
}
