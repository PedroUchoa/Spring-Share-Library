package com.example.digital_library.services;

import com.example.digital_library.dtos.CreateAuthorDto;
import com.example.digital_library.dtos.ReturnAuthorDto;
import com.example.digital_library.dtos.ReturnBookDto;
import com.example.digital_library.infra.exceptions.AuthorNotFoundException;
import com.example.digital_library.models.Author;
import com.example.digital_library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public ReturnAuthorDto createAuthor(CreateAuthorDto authorDto){
        Author author  = authorRepository.save(new Author(authorDto));
        return new ReturnAuthorDto(author);
    }

    public Page<ReturnAuthorDto> getAllAuthors(Pageable pageable){
      return authorRepository.findAll(pageable).map(ReturnAuthorDto::new);
    }

    public ReturnAuthorDto getAuthorById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
        return new ReturnAuthorDto(author);
    }

    public List<ReturnBookDto> getBooksByAuthor(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
        return author.getBooks().stream().map(ReturnBookDto::new).toList();
    }

    public ReturnAuthorDto updateAuthor(CreateAuthorDto createAuthorDto, Long id){
       Author author= authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
       author.update(createAuthorDto);
       authorRepository.save(author);
       return new ReturnAuthorDto(author);
    }

    public void deleteAuthor(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
        authorRepository.delete(author);
    }





}
