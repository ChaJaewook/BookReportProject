package com.bookreport.core.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BookOrder extends BasedEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    private Long orderPrice;

    private int count;

}
