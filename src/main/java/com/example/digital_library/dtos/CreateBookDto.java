package com.example.digital_library.dtos;

import java.math.BigDecimal;

public record CreateBookDto(String title,
                            String isbn,
                            Integer publicationYear,
                            BigDecimal price,
                            Long authorId,
                            Long categoryId) {
}
