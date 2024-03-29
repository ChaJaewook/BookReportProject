package com.bookreport.core.controller;

import com.bookreport.core.domain.MemberSexual;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    private Long id;

    @NotEmpty(message="회원 이름은 필수 입니다.")
    private String name;
    private int age;
    private MemberSexual sexual;
    private String street;
    private String city;
    private String zipcode;

}
