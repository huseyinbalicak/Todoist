package com.appcent.todoist.controller;

import com.appcent.todoist.dto.CategoryResponseDto;
import com.appcent.todoist.dto.save.CategorySaveRequestDto;
import com.appcent.todoist.dto.update.CategoryUpdateRequestDto;
import com.appcent.todoist.response.RestResponse;
import com.appcent.todoist.service.CategoryService;
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

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto){
        CategoryResponseDto categoryResponseDto = categoryService.update(categoryUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(categoryResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
