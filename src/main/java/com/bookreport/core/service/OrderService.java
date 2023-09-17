package com.bookreport.core.service;

import com.bookreport.core.domain.*;
import com.bookreport.core.repository.BookRepository;
import com.bookreport.core.repository.MemberRepository;
import com.bookreport.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true0)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Transactional
    public Long order(Long memberId, Long bookId, int count)
    {
        Book findBook=bookRepository.findOne(bookId);
        Member findMember=memberRepository.findOne(memberId);

        Delivery delivery=new Delivery();
        delivery.setAddress(findMember.getAddress());



    }
}
