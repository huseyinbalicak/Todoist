package com.appcent.todoist.mapper;

import com.appcent.todoist.dto.TodoResponseDto;
import com.appcent.todoist.dto.save.TodoSaveRequestDto;
import com.appcent.todoist.dto.update.TodoUpdateRequestDto;
import com.appcent.todoist.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoResponseDto convertToTodoResponseDto(Todo todo);

    List<TodoResponseDto> convertToTodoResponseDtoList (List<Todo> todoList);

    Todo convertToTodo(TodoSaveRequestDto todoSaveRequestDto);

    Todo convertToTodo(TodoUpdateRequestDto todoUpdateRequestDto);
}
