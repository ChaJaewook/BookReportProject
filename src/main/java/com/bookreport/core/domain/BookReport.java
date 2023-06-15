package com.bookreport.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class BookReport {

    @GeneratedValue
    @Id
    @Column(name="bookreport_id")
    private long id;

    //독후감을 쓴 회원에 대한 Mapping
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;


    //독후감 내용
    private String content;

    //읽은 날짜
    private LocalDateTime readDate;

    //생성메서드
    public static BookReport createBookReport(Member member, Book book, String content)
    {
        BookReport bookReport=new BookReport();
        bookReport.setBook(book);
        bookReport.setMember(member);
        bookReport.setContent(content);

        return bookReport;
    }
}
