package com.bookreport.core.domain;

import lombok.Data;

@Data
public class MemberResponseDto {
    private Long id;
    private String name;
    private int age;
    private Address address;
    private MemberSexual sexual;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
        this.address = member.getAddress();
        this.sexual = member.getMemberSexual();
    }
}
