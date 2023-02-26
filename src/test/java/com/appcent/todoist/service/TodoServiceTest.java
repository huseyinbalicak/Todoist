package com.appcent.todoist.service;

import com.appcent.todoist.dto.save.TodoSaveRequestDto;
import com.appcent.todoist.dto.TodoResponseDto;
import com.appcent.todoist.dto.update.TodoUpdateRequestDto;
import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.model.Todo;
import com.appcent.todoist.repository.TodoRepository;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("When finding all todos with an empty todo list, the service")
    void shouldFindAll() {
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        assertEquals(0, todoResponseDtoList.size());
    }

    @Test
    @DisplayName("When finding all todos with a non-empty todo list, the service")
    void shouldFindAllWhenReturnsTodos() {
        Todo todo = mock(Todo.class);
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo);
        when(todoRepository.findAll()).thenReturn(todoList);
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        assertEquals(1, todoResponseDtoList.size());
    }

    @Test
    @DisplayName("When finding a todo by ID, the service")
    void shouldFindById() {
        Todo todo = mock(Todo.class);
        when(todo.getId()).thenReturn(1L);
        when(todoRepository.findById(anyLong())).thenReturn(Optional.of(todo));
        TodoResponseDto result = todoService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("When finding all todos but the todo list is null, the service")
    void shouldFindAllWhenTodoListISNull() {
        when(todoRepository.findAll()).thenReturn(null);
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        assertNull(todoResponseDtoList);
    }

    @Test
    @DisplayName("When finding a todo by ID that does not exist, the service")
    void shouldNotFindByIdWhenIdDoesNotExist() {
        when(todoRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> todoService.findById(-1L));
    }

    @Test
    @DisplayName("When saving a todo, the service")
    void shouldSave() {
        TodoSaveRequestDto todoSaveRequestDto = mock(TodoSaveRequestDto.class);
        Todo todo = mock(Todo.class);
        when(todo.getId()).thenReturn(1L);
        when(todoRepository.save(any())).thenReturn(todo);
        TodoResponseDto result = todoService.save(todoSaveRequestDto);
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("When updating an existing todo, the service")
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
    @DisplayName("When attempting to update a non-existent todo, the service")
    void shouldNotUpdate() {
        TodoUpdateRequestDto todoUpdateRequestDto = mock(TodoUpdateRequestDto.class);
        Todo todo = mock(Todo.class);
        when(todoRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
        assertThrows(EntityNotFoundException.class, () -> todoService.update(todo.getId(),todoUpdateRequestDto));

    }

    @Test
    @DisplayName("When checking if an existing todo exists, the service")
    void shouldExist() {
        when(todoRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        boolean isExist = todoService.isExist(1L);
        assertTrue(isExist);
    }

    @Test
    @DisplayName("When checking if a non-existent todo exists, the service")
    void shouldNotExist() {
        when(todoRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
        boolean isExist = todoService.isExist(1L);
        assertFalse(isExist);
    }

    @Test
    @DisplayName("When deleting a todo with a valid ID, the service")
    void shouldDelete(){
        Todo todo = mock(Todo.class);
        todo.setId(1L);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        Todo deletedTodo = todoService.delete(1L);
        verify(todoRepository).delete(todo);
        assertEquals(todo, deletedTodo);

    }

    @Test
    @DisplayName("When attempting to delete a non-existent todo, the service")
    void shouldNotDelete()
    {
//        doThrow(EntityNotFoundException.class).when(todoRepository).deleteById(anyLong());
//        assertThrows(EntityNotFoundException.class,()->todoService.delete(anyLong()));
//        verify(todoRepository).deleteById(anyLong());

        when(todoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> todoService.delete(1L));

        verify(todoRepository, never()).delete(any());


    }

    @Test
    @DisplayName("When attempting to delete a todo with null ID, the service")
    void shouldThrowEntityNotFoundExceptionWhenIdIsNull(){
        assertThrows(EntityNotFoundException.class, () -> todoService.delete(null));
        verify(todoRepository, never()).deleteById(anyLong());
    }
}