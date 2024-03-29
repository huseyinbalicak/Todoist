package com.appcent.todoist.controller;

import com.appcent.todoist.dto.CategoryResponseDto;
import com.appcent.todoist.service.CategoryService;
import com.appcent.todoist.dto.TodoResponseDto;
import com.appcent.todoist.dto.save.CategorySaveRequestDto;
import com.appcent.todoist.dto.update.CategoryUpdateRequestDto;
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
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity findAll(){
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.findAll();
        return ResponseEntity.ok(RestResponse.of(categoryResponseDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        CategoryResponseDto categoryResponseDto = categoryService.findById(id);
        return ResponseEntity.ok(RestResponse.of(categoryResponseDto));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CategorySaveRequestDto categorySaveRequestDto){
        CategoryResponseDto categoryResponseDto = categoryService.save(categorySaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(categoryResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id,@Valid @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto){
        CategoryResponseDto categoryResponseDto = categoryService.update(id,categoryUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(categoryResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity getAllTodoByCategoriesId(@PathVariable  Long id) {

        List<TodoResponseDto> todoResponseDtoList = todoService.findByCategory(id);
        return ResponseEntity.ok(RestResponse.of(todoResponseDtoList));

    }

    @GetMapping("/users/{id}")
    public ResponseEntity getAllCategoriesByUserId(@PathVariable Long id) {
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.findAllByUserId(id);
        return ResponseEntity.ok(RestResponse.of(categoryResponseDtoList));
    }

}
