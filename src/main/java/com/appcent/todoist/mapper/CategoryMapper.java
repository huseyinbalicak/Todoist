package com.appcent.todoist.mapper;

import com.appcent.todoist.dto.CategoryResponseDto;
import com.appcent.todoist.dto.save.CategorySaveRequestDto;
import com.appcent.todoist.dto.update.CategoryUpdateRequestDto;
import com.appcent.todoist.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponseDto convertToCategoryResponseDto(Category category);

    List<CategoryResponseDto> convertToCategoryResponseDtoList (List<Category> categoryList);

    Category convertToCategory(CategorySaveRequestDto categorySaveRequestDto);

    Category convertToCategory(CategoryUpdateRequestDto categoryUpdateRequestDto);
}
