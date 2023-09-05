package com.bookreport.core.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@ToString
public class BookReport extends BasedEntity{

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
    @Lob
    private String content;

    //읽은 날짜
    private LocalDate readDate;

    @Builder
    public BookReport(long id, Member member, Book book, String content, LocalDate readDate) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.content = content;
        this.readDate = readDate;
    }



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
