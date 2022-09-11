package com.appcent.todoist.dto.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoSaveRequestDto {

    private String description;
    @NotNull
    @Size(min = 8)
    private String tittle;
    private CategorySaveRequestDto category;
}
