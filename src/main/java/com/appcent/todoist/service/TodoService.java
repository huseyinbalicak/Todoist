package com.appcent.todoist.service;

import com.appcent.todoist.dto.TodoResponseDto;
import com.appcent.todoist.dto.save.TodoSaveRequestDto;
import com.appcent.todoist.dto.update.TodoUpdateRequestDto;
import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.mapper.TodoMapper;
import com.appcent.todoist.model.Todo;
import com.appcent.todoist.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDto> findAll(){
        List<Todo> todoList = todoRepository.findAll();
        return TodoMapper.INSTANCE.convertToTodoResponseDtoList(todoList);
    }

    public List<TodoResponseDto> findByCategory(Long categoryId) {
        List<Todo> todoList = todoRepository.findTodoByCategoryId(categoryId);
        return TodoMapper.INSTANCE.convertToTodoResponseDtoList(todoList);

    }

    public TodoResponseDto save(TodoSaveRequestDto todoSaveRequestDto){

        if (todoSaveRequestDto == null){
            throw new EntityNotFoundException("VALUES CANNOT BE NULL");
        }

        Todo todo = TodoMapper.INSTANCE.convertToTodo(todoSaveRequestDto);
        todo = todoRepository.save(todo);
        return TodoMapper.INSTANCE.convertToTodoResponseDto(todo);
    }

    public TodoResponseDto update(TodoUpdateRequestDto todoUpdateRequestDto) {

        boolean isExist = isExist(todoUpdateRequestDto.getId());
        if (!isExist){
            throw new EntityNotFoundException("Todo not found");
        }

        Todo todo = TodoMapper.INSTANCE.convertToTodo(todoUpdateRequestDto);
        todo = todoRepository.save(todo);
        return TodoMapper.INSTANCE.convertToTodoResponseDto(todo);
    }

    public void delete(Long id) {
        if (id == null) {
            log.error("Todo id is null");
            throw new EntityNotFoundException("No todo found with ID = " + id);
        }
        todoRepository.deleteById(id);
    }

    public boolean isExist(Long id){
        return todoRepository.existsById(id);
    }

    public Todo findByIdWithControl(Long id){
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (optionalTodo.isPresent()){
            return optionalTodo.get();
        } else {
            throw new EntityNotFoundException("No todo found with ID = " + id);
        }
    }

    public TodoResponseDto findById(Long id){

        Todo todo = findByIdWithControl(id);
        return TodoMapper.INSTANCE.convertToTodoResponseDto(todo);
    }
}
