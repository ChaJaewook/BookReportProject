package com.bookreport.core.controller;

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
    private String sexual;

}
