package com.bookreport.core.service;

import com.bookreport.core.controller.MemberForm;
import com.bookreport.core.domain.*;
import com.bookreport.core.repository.MemberRepository;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        findMember.setMemberSexual(form.getSexual());
        Address editAddress=new Address(form.getStreet(), form.getCity(), form.getZipcode());
        findMember.setAddress(editAddress);

    }

    @Transactional
    public Long deleteMember(Long id)
    {
        Member removeMember= memberRepository.findOne(id);
        memberRepository.delete(removeMember);
        return id;
    }

    public MemberListResponseDto searchMember(String type, String card, int offset)
    {
        QueryResults<Member> results=memberRepository.memberListSearch(type, card, offset);
        List<Member> findMembers=results.getResults();

        List<MemberResponseDto> memberResponseList= findMembers
                .stream()
                .map(o-> new MemberResponseDto(o))
                .collect(Collectors.toList());

        long count=results.getTotal();

        return new MemberListResponseDto(count, memberResponseList);

    }
}
