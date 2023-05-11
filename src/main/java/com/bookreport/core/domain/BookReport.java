package com.bookreport.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class BookReport {

    @GeneratedValue
    @Id
    @Column(name="book_id")
    private long id;

    //독후감을 쓴 회원에 대한 Mapping
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    Member member;

    @ManyToOne
    @JoinColumn(name="book_id")
    Book book;

}
