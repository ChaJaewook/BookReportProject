package com.bookreport.core.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookForm {

    @NotEmpty(message="책 이름은 필수 입니다.")
    private String title;

    private String author;

    private double grade;

    @NotEmpty(message = "isbn 코드는 필수 입니다.")
    private String isbn;

    private long sold;

    private LocalDateTime publish_date;

}
