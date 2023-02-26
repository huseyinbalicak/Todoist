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
    @NotNull
    @Size(min = 8)
    private String tittle;
    @NotNull
    private LocalDate due_date;
    @NotNull
    private Boolean completed;
    private CategorySaveRequestDto category;
}
