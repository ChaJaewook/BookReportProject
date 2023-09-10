package com.bookreport.core.controller;

import com.bookreport.core.domain.BookCategory;
import com.bookreport.core.domain.Grade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookForm {
    private Long id;

    @NotEmpty(message="책 이름은 필수 입니다.")
    private String title;
    private String author;
    private Grade grade;

    @NotEmpty(message = "isbn 코드는 필수 입니다.")
    private String isbn;
    private Long price;
    private int stockQuantity;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate publishDate;
    private BookCategory category;
    private MultipartFile imgFile;
}
