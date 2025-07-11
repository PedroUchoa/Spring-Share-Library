package com.example.digital_library.dtos;

import com.example.digital_library.models.Category;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDto(
        @NotBlank(message = "Name Can't be Empty")
        String name,
        @NotBlank(message = "Description Can't be Empty")
        String description) {

    public CreateCategoryDto(Category category) {
       this(category.getName(), category.getDescription());
    }
}
