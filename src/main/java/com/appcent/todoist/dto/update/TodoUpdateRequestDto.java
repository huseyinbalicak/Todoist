package com.appcent.todoist.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequestDto {

    private Long id;
    private String description;
    private String tittle;
    //private ZonedDateTime startDate;
    //private boolean favorite;
    //private boolean done;
    private CategoryUpdateRequestDto category;

}
