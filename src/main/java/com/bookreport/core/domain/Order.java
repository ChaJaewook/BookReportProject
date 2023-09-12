package com.bookreport.core.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    List<BookOrder> bookOrders=new ArrayList<>();

    private LocalDate orderDate;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private OrderStatus status;

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addBookOrder(BookOrder bookOrder)
    {
        bookOrders.add(bookOrder);
        bookOrder.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void createOrder(Member member,  Delivery delivery, BookOrder... bookOrders)
    {
        this.member=member;
        for(BookOrder item : bookOrders)
        {
            this.bookOrders.add(item);
        }
        this.delivery=delivery;
        this.status=OrderStatus.ORDER;
        this.orderDate=LocalDate.now();
    }

    public void cancel()
    {

    }
}
