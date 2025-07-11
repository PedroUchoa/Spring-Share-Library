package com.example.digital_library.controllers;

import com.example.digital_library.dtos.CreateCategoryDto;
import com.example.digital_library.dtos.ReturnBookDto;
import com.example.digital_library.dtos.ReturnCategoryDto;
import com.example.digital_library.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReturnCategoryDto> createCategory(@RequestBody @Valid CreateCategoryDto createCategoryDto, UriComponentsBuilder componentsBuilder){
        ReturnCategoryDto categoryReturn = categoryService.createCategory(createCategoryDto);
        URI location = componentsBuilder
                .path("/{id}")
                .buildAndExpand(categoryReturn.id())
                .toUri();
        return ResponseEntity.created(location).body(categoryReturn);
    }

    @GetMapping
    public ResponseEntity<Page<ReturnCategoryDto>> getCategories (@PageableDefault(sort = {"id"}) Pageable pageable){
        Page<ReturnCategoryDto> categoryReturn = categoryService.getCategories(pageable);
        return ResponseEntity.ok(categoryReturn);
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<ReturnBookDto>> getBooksByCategory(@PathVariable Long id){
        List<ReturnBookDto> booksReturn = categoryService.getBooksByCategory(id);
        return ResponseEntity.ok(booksReturn);
    }

}
