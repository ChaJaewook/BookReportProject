package com.bookreport.core.domain;

import com.bookreport.core.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    //private double grade;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    private String isbn;
    private Long price;
    private int stockQuantity;
    private LocalDate publishDate;
    private String imgName; //이미지 파일명
    private String imgPath; //이미지 조회 경로


    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<BookReport> bookReport=new ArrayList<>();

    public void updateBook(String author, String title, Grade grade,
                           BookCategory category, Long price, int stockQuantity)
    {
        this.author=author;
        this.title=title;
        this.grade=grade;
        this.bookCategory=category;
        this.price=price;
        this.stockQuantity=stockQuantity;
    }

    protected Book() {
    }

    public void removeStock(int count)
    {
        int restStock=this.stockQuantity-count;
        if(restStock<0)
            throw new NotEnoughStockException("need more stack");

        this.stockQuantity=restStock;
    }

    public void addStock(int stock){
        this.stockQuantity+=stock;
    }


    @Builder
    public Book(BookCategory bookCategory, String title, String author, Grade grade, String isbn, Long price,
                int stockQuantity, LocalDate publishDate, String imgName, String imgPath) {
        this.bookCategory = bookCategory;
        this.title = title;
        this.author = author;
        this.grade = grade;
        this.isbn = isbn;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.publishDate = publishDate;
        this.imgName = imgName;
        this.imgPath = imgPath;

    }
}
