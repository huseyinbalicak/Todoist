package com.appcent.todoist.service;

import com.appcent.todoist.dto.save.TodoSaveRequestDto;
import com.appcent.todoist.dto.TodoResponseDto;
import com.appcent.todoist.dto.update.TodoUpdateRequestDto;
import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.model.Todo;
import com.appcent.todoist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoService todoService;

    @Test
    void shouldFindAll() {
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        assertEquals(0, todoResponseDtoList.size());
    }

    @Test
    void shouldFindAllWhenReturnsTodos() {
        Todo todo = mock(Todo.class);
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo);
        when(todoRepository.findAll()).thenReturn(todoList);
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        assertEquals(1, todoResponseDtoList.size());
    }

    @Test
    void shouldFindById() {
        Todo todo = mock(Todo.class);
        when(todo.getId()).thenReturn(1L);
        when(todoRepository.findById(anyLong())).thenReturn(Optional.of(todo));
        TodoResponseDto result = todoService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldFindAllWhenTodoListISNull() {
        when(todoRepository.findAll()).thenReturn(null);
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        assertNull(todoResponseDtoList);
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist() {
        when(todoRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> todoService.findById(-1L));
    }

    @Test
    void shouldSave() {
        TodoSaveRequestDto todoSaveRequestDto = mock(TodoSaveRequestDto.class);
        Todo todo = mock(Todo.class);
        when(todo.getId()).thenReturn(1L);
        when(todoRepository.save(any())).thenReturn(todo);
        TodoResponseDto result = todoService.save(todoSaveRequestDto);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldUpdate() {

        TodoUpdateRequestDto todoUpdateRequestDto = mock(TodoUpdateRequestDto.class);
        Todo todo = mock(Todo.class);
        when(todo.getId()).thenReturn(1L);
        when(todoRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        when(todoRepository.save(any())).thenReturn(todo);
        TodoResponseDto result = todoService.update(todo.getId(),todoUpdateRequestDto);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldNotUpdate() {
        TodoUpdateRequestDto todoUpdateRequestDto = mock(TodoUpdateRequestDto.class);
        Todo todo = mock(Todo.class);
        when(todoRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
        assertThrows(EntityNotFoundException.class, () -> todoService.update(todo.getId(),todoUpdateRequestDto));

    }

    @Test
    void shouldExist() {
        when(todoRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        boolean isExist = todoService.isExist(1L);
        assertTrue(isExist);
    }

    @Test
    void shouldNotExist() {
        when(todoRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
        boolean isExist = todoService.isExist(1L);
        assertFalse(isExist);
    }

    @Test
    void shouldDelete(){
        doNothing().when(todoRepository).deleteById(anyLong());
        todoService.delete(1L);
        verify(todoRepository).deleteById(anyLong());
    }

    @Test
    void shouldNotDelete()
    {
        doThrow(EntityNotFoundException.class).when(todoRepository).deleteById(anyLong());
        assertThrows(EntityNotFoundException.class,()->todoService.delete(anyLong()));
        verify(todoRepository).deleteById(anyLong());


    }
}