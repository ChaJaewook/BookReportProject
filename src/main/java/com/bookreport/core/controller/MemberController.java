package com.bookreport.core.controller;

import com.bookreport.core.domain.Member;
import com.bookreport.core.domain.MemberSexual;
import com.bookreport.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    //회원가입
    @GetMapping("/members/new")
    public String createForm(Model model)
    {
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result)
    {
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        MemberSexual memS=null;

        if(form.getSexual().equals("man"))
            memS=MemberSexual.MAN;
        else if(form.getSexual().equals("woman"))
            memS= MemberSexual.WOMAN;

        Member member=Member.builder()
                .memberSexual(memS)
                .name(form.getName())
                .age(form.getAge())
                .build();

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model)
    {
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    @GetMapping("/members/{memberId}/edit")
    public String updateMemberForm(@PathVariable("memberId") Long memberId, Model model)
    {
        Member findMember=memberService.findOne(memberId);
        MemberForm form=new MemberForm();
        form.setId(findMember.getId());
        form.setAge(findMember.getAge());
        form.setSexual(String.valueOf(findMember.getMemberSexual()== MemberSexual.MAN?MemberSexual.MAN:MemberSexual.WOMAN));
        form.setName(findMember.getName());

        model.addAttribute("member",form);
        return "/members/updateMemberForm";

    }

    @PostMapping("members/{memberId}/edit")
    public String updateMember(@PathVariable("memberId")Long id, MemberForm form)
    {
        memberService.updateMember(id, form);
        return "redirect:/members";
    }

    @GetMapping("members/{memberId}/delete")
    public String deleteMember(@PathVariable("memberId")Long id)
    {
        memberService.deleteMember(id);
        return "redirect:/members";
    }

}
