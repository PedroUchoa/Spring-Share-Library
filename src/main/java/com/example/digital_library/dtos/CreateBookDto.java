package com.example.digital_library.dtos;

import com.example.digital_library.infra.customValidation.NotFutureYear;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Year;

public record CreateBookDto(
        @NotBlank(message = "Title Can't be Empty")
        String title,
        @NotBlank(message = "ISBN Can't be Empty")
        @Size(min = 10, max = 13, message = "ISBN size has to be between 10 and 13 digits")
        String isbn,
        @PositiveOrZero(message = "Year Can't Be Negative")
        @NotNull(message = "Publication Year Can't be Null")
        @NotFutureYear
        Integer publicationYear,
        @NotNull(message = "Author Id Can't be Null")
        @Min(value = 1, message = "Author Id Can't be Empty")
        Long authorId,
        @PositiveOrZero(message = "Price Can't be Negative")
        @NotNull(message = "Price Can't be Null")
        BigDecimal price,
        @NotNull(message = "Category Id Can't be Null")
        @Min(value = 1, message = "Category Id Can't be Empty")
        Long categoryId) {
}
