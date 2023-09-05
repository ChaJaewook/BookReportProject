package com.bookreport.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Book extends BasedEntity{
    @Id
    @GeneratedValue
    @Column(name="book_id")
    private long id;

    //카테고리
    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    private String title;

    private String author;

    private double grade;

    private String isbn;

    private Long price;

    private int stockQuantity;

    private LocalDateTime publish_date;

    private String imgName; //이미지 파일명

    private String imgPath; //이미지 조회 경로

    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<BookReport> bookReport=new ArrayList<>();


}
