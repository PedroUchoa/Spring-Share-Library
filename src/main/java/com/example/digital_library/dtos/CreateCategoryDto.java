package com.example.digital_library.dtos;

import com.example.digital_library.models.Category;

public record CreateCategoryDto(String name, String description) {

    public CreateCategoryDto(Category category) {
       this(category.getName(), category.getDescription());
    }
}
