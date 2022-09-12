package com.appcent.todoist.service;

import com.appcent.todoist.dto.CategoryResponseDto;
import com.appcent.todoist.dto.save.CategorySaveRequestDto;
import com.appcent.todoist.dto.update.CategoryUpdateRequestDto;
import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.mapper.CategoryMapper;
import com.appcent.todoist.model.Category;
import com.appcent.todoist.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponseDto> findAll(){
        List<Category> categoryList = categoryRepository.findAll();
        return CategoryMapper.INSTANCE.convertToCategoryResponseDtoList(categoryList);
    }

    public CategoryResponseDto save(CategorySaveRequestDto categorySaveRequestDto){
        Category category = CategoryMapper.INSTANCE.convertToCategory(categorySaveRequestDto);
        category = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.convertToCategoryResponseDto(category);
    }

    public CategoryResponseDto update(CategoryUpdateRequestDto categoryUpdateRequestDto) {

        boolean isExist = isExist(categoryUpdateRequestDto.getId());
        if (!isExist){
            throw new EntityNotFoundException("Category not found");
        }

        Category category = CategoryMapper.INSTANCE.convertToCategory(categoryUpdateRequestDto);
        category = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.convertToCategoryResponseDto(category);
    }

    public void delete(Long id) {
        if (id == null) {
            log.error("Category id is null");
            throw new EntityNotFoundException("No category found with ID = " + id);
        }
        categoryRepository.deleteById(id);
    }


    public boolean isExist(Long id){
        return categoryRepository.existsById(id);
    }

    public Category findByIdWithControl(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        } else {
            throw new EntityNotFoundException("No category found with ID = " + id);
        }
    }


    public CategoryResponseDto findById(Long id){

        Category category = findByIdWithControl(id);
        return CategoryMapper.INSTANCE.convertToCategoryResponseDto(category);
    }


    public List<CategoryResponseDto> findAllByUserId(Long userId) {

        List<Category> categoryList = categoryRepository.findCategoryByUserId(userId);
        return CategoryMapper.INSTANCE.convertToCategoryResponseDtoList(categoryList);
    }


}
