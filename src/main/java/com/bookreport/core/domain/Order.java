package com.bookreport.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="Orders")
@Setter
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<BookOrder> bookOrders=new ArrayList<>();

    private LocalDate orderDate;

    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
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

    public static Order createOrder(Member member,  Delivery delivery, BookOrder... bookOrders)
    {


        /*Order order= Order.builder()
                .member(member)
                .delivery(delivery)
                .bookOrders(bookOrderList)
                .build();*/

        Order order=new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(BookOrder item: bookOrders)
        {
            order.addBookOrder(item);
        }

        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDate.now());

        return order;
    }

    public void cancel()
    {

    }
}
