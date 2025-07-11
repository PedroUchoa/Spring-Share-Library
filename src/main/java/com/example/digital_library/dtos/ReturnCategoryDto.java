package com.example.digital_library.dtos;

import com.example.digital_library.models.Category;

public record ReturnCategoryDto(Long id , String name, String description) {

    public ReturnCategoryDto(Category category) {
        this(category.getId(), category.getName(), category.getDescription());
    }
}
