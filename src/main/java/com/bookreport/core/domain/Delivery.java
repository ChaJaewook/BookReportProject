package com.bookreport.core.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch=FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
