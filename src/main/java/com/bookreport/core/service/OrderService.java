package com.bookreport.core.service;

import com.bookreport.core.domain.*;
import com.bookreport.core.repository.BookRepository;
import com.bookreport.core.repository.MemberRepository;
import com.bookreport.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
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

        //Delivery delivery=new Delivery();
        //delivery.setAddress(findMember.getAddress());

        Delivery delivery=Delivery.builder().address(findMember.getAddress()).build();


        BookOrder orderItem=BookOrder.createBookOrder(findBook, count);

        Order order=Order.createOrder(findMember,delivery,orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId)
    {
        //조회
        Order order=orderRepository.findById(orderId);
        order.cancel();
    }

    public List<Order> findOrders(){
        List<Order> orderList=orderRepository.findAll();
        return orderList;
    }
}
