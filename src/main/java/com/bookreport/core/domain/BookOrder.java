package com.bookreport.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    public void createBookOrder(Book book, int count)
    {
        this.book=book;
        this.orderPrice=book.getPrice();
        this.count=count;
    }

}
