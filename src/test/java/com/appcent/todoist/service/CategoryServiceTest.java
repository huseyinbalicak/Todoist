package com.appcent.todoist.service;

import com.appcent.todoist.dto.CategoryResponseDto;
import com.appcent.todoist.dto.save.CategorySaveRequestDto;
import com.appcent.todoist.dto.update.CategoryUpdateRequestDto;
import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.model.Category;
import com.appcent.todoist.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    @Test
    void shouldFindAll() {
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.findAll();
        assertEquals(0, categoryResponseDtoList.size());
    }

    @Test
    void shouldFindAllWhenReturnsCategories() {
        Category category = mock(Category.class);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.findAll();
        assertEquals(1, categoryResponseDtoList.size());
    }

    @Test
    void shouldFindById() {
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(1L);
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        CategoryResponseDto result = categoryService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldFindAllWhenCategoryListISNull() {
        when(categoryRepository.findAll()).thenReturn(null);
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.findAll();
        assertNull(categoryResponseDtoList);
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist() {
        when(categoryRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(-1L));
    }

    @Test
    void shouldSave() {
        CategorySaveRequestDto categorySaveRequestDto = mock(CategorySaveRequestDto.class);
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(1L);
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryResponseDto result = categoryService.save(categorySaveRequestDto);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldUpdate() {

        CategoryUpdateRequestDto categoryUpdateRequestDto = mock(CategoryUpdateRequestDto.class);
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(1L);
        when(categoryRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryResponseDto result = categoryService.update(categoryUpdateRequestDto);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldNotUpdate() {
        CategoryUpdateRequestDto categoryUpdateRequestDto = mock(CategoryUpdateRequestDto.class);
        when(categoryRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
        assertThrows(EntityNotFoundException.class, () -> categoryService.update(categoryUpdateRequestDto));

    }

    @Test
    void shouldExist() {
        when(categoryRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        boolean isExist = categoryService.isExist(1L);
        assertTrue(isExist);
    }

    @Test
    void shouldNotExist() {
        when(categoryRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
        boolean isExist = categoryService.isExist(1L);
        assertFalse(isExist);
    }

    @Test
    void shouldDelete(){
        doNothing().when(categoryRepository).deleteById(anyLong());
        categoryService.delete(1L);
        verify(categoryRepository).deleteById(anyLong());
    }

    @Test
    void shouldNotDelete()
    {
        doThrow(EntityNotFoundException.class).when(categoryRepository).deleteById(anyLong());
        assertThrows(EntityNotFoundException.class,()->categoryService.delete(anyLong()));
        verify(categoryRepository).deleteById(anyLong());


    }

}