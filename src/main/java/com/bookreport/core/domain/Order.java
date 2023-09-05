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
}
