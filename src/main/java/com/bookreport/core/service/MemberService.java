package com.bookreport.core.service;

import com.bookreport.core.controller.MemberForm;
import com.bookreport.core.domain.Member;
import com.bookreport.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    @NoArgsConstructor : 파라미터가 없는 기본생성자(Student std=new Student() )
    @RequiredArgsConstructor : final이나 @NonNull 인 필드 값만 파라미터로 받는 생성자
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    //혹은
    /*@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    @Transactional
    public Long join(Member member)
    {
        //중복 검증 로직 추가 예정
        memberRepository.save(member);
        return member.getId();
    }


    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    @Transactional
    public Member findOne(Long memberId)
    {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void updateMember(Long memberId, MemberForm form){
        Member findMember= memberRepository.findOne(memberId);

        findMember.setName(form.getName());
        findMember.setAge(form.getAge());
        findMember.setName(form.getName());

    }
}
