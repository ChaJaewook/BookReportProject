package com.bookreport.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch=FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Delivery() {
    }

    @Builder
    public Delivery( Order order, Address address, OrderStatus status) {
        this.order = order;
        this.address = address;
        this.status = status;
    }
}
