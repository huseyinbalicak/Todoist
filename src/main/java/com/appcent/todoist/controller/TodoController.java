package com.appcent.todoist.controller;

import com.appcent.todoist.dto.TodoResponseDto;
import com.appcent.todoist.dto.save.TodoSaveRequestDto;
import com.appcent.todoist.dto.update.TodoUpdateRequestDto;
import com.appcent.todoist.response.RestResponse;
import com.appcent.todoist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity findAll(){
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        return ResponseEntity.ok(RestResponse.of(todoResponseDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        TodoResponseDto todoResponseDto = todoService.findById(id);
        return ResponseEntity.ok(RestResponse.of(todoResponseDto));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody TodoSaveRequestDto todoSaveRequestDto){
        TodoResponseDto todoResponseDto = todoService.save(todoSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(todoResponseDto));
    }


    @PutMapping
    public ResponseEntity update(@Valid  @RequestBody TodoUpdateRequestDto todoUpdateRequestDto){
        TodoResponseDto todoResponseDto = todoService.update(todoUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(todoResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        todoService.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
