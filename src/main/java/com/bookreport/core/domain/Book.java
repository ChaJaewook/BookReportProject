package com.bookreport.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    @Column(name="book_id")
    private long id;

    //카테고리
    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    //제목
    private String title;

    //저자
    private String author;

    //평점
    private double grade;

    //isbn코드
    private String isbn;

    //판매량
    private long sold;

    //책 출시일
    private LocalDateTime publish_date;

    private String imgName; //이미지 파일명

    private String imgPath; //이미지 조회 경로

    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<BookReport> bookReport=new ArrayList<>();
}
