package com.bookreport.core.service;

import com.bookreport.core.domain.Member;
import com.bookreport.core.repository.MemberRepository;
import com.sun.source.tree.MemberReferenceTree;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입()
    {
        //given
        Member member=Member.builder().name("jaewook").build();

        //when
        Long saveId=memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }
}