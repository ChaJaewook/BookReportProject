package com.bookreport.core.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Builder
    public BookOrder(Long id, Order order, Book book, Long orderPrice, int count) {
        this.id = id;
        this.order = order;
        this.book = book;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static BookOrder createBookOrder(Book book, int count)
    {
        BookOrder orderItem=BookOrder
                .builder()
                .book(book)
                .orderPrice(book.getPrice())
                .count(count)
                .build();

        book.removeStock(count);

        return orderItem;
    }

    public void cancel(){
        getBook().addStock(count);
    }

    public long getTotalPrice()
    {
        return getOrderPrice()*getCount();
    }

}
