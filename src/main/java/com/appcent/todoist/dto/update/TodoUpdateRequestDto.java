package com.appcent.todoist.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequestDto {

    private String description;
    private String tittle;
    private LocalDate due_date;
    private Boolean completed;
    private CategoryUpdateRequestDto category;

}
