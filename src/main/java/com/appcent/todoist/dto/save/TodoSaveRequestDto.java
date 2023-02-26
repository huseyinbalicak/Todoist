package com.appcent.todoist.dto.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoSaveRequestDto {

    private String description;
    @NotNull(message = "title cannot be null")
    @Size(min = 8)
    private String tittle;
    @NotNull(message = "due_date cannot be null")
    private LocalDate due_date;
    @NotNull(message = "completed cannot be null")
    private Boolean completed;
    private CategorySaveRequestDto category;
}
