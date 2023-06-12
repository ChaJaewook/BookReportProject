package com.bookreport.core.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookForm {
    private Long id;

    @NotEmpty(message="책 이름은 필수 입니다.")
    private String title;

    private String author;

    private String grade;

    @NotEmpty(message = "isbn 코드는 필수 입니다.")
    private String isbn;

    private long sold;

    private String publish_date;

    private String category;

    private MultipartFile imgFile;
}
