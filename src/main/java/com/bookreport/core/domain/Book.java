package com.bookreport.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private BookCategory bookCategory;

    private String title;

    private String author;

    private double grade;

    private String isbn;

    long sold;

    @OneToMany(mappedBy = "book")
    private List<BookReport> bookReport=new ArrayList<>();
}
