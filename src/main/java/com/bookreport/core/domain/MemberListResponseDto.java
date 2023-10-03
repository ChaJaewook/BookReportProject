package com.bookreport.core.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberListResponseDto {
    private long count;
    private List<MemberResponseDto> response=new ArrayList<>();

    public MemberListResponseDto(long count, List<MemberResponseDto> response) {
        this.count = count;
        this.response = response;
    }
}
