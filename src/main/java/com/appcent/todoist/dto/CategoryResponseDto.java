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
public class CategoryResponseDto {

    private Long id;
    private String description;
    private UserResponseDto user;
    private List<TodoResponseDto> todoList;
}
