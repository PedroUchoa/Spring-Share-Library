package com.example.digital_library.services;

import com.example.digital_library.dtos.CreateCategoryDto;
import com.example.digital_library.dtos.ReturnBookDto;
import com.example.digital_library.dtos.ReturnCategoryDto;
import com.example.digital_library.models.Book;
import com.example.digital_library.models.Category;
import com.example.digital_library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ReturnCategoryDto createCategory(CreateCategoryDto categoryDto){
        Category category = categoryRepository.save(new Category(categoryDto));
        return new ReturnCategoryDto(category);
    }

    public Page<ReturnCategoryDto> getCategories(Pageable pageable){
        return categoryRepository.findAll(pageable).map(ReturnCategoryDto::new);
    }

    public List<ReturnBookDto> getBooksByCategory(Long id){
        Category category = categoryRepository.getReferenceById(id);
        return category.getBooks().stream().map(ReturnBookDto::new).toList();
    }


}
