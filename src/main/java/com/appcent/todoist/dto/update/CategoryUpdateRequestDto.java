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
public class CategoryUpdateRequestDto {

    private Long id;
    private String description;
    private UserUpdateRequestDto user;
    private List<TodoUpdateRequestDto> todoList;


}
