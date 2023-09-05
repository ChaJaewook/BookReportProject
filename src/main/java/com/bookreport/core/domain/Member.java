package com.bookreport.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends BasedEntity{

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberSexual memberSexual;

    private int age;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BookReport> bookReports=new ArrayList<>();

    @OneToMany(mappedBy="order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Order> order=new ArrayList<>();


}
