package com.bookreport.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    String name;

    MemberSexual memberSexual;

    int age;

    @OneToMany(mappedBy = "member")
    private List<BookReport> bookReports=new ArrayList<>();


}
