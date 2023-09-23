package com.bookreport.core.controller;

import com.bookreport.core.domain.Book;
import com.bookreport.core.domain.Member;
import com.bookreport.core.service.BookService;
import com.bookreport.core.service.MemberService;
import com.bookreport.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final MemberService memberService;
    private final BookService bookService;
    private final OrderService orderService;

    @GetMapping("/orders/new")
    public String createForm(Model model)
    {
        List<Member> members=memberService.findMembers();
        List<Book> books= bookService.findBooks();
        model.addAttribute("items",books);
        model.addAttribute("members",members);

        return "orders/ordersForm";
    }

    @PostMapping("/orders")
    public String order(
            @RequestParam("memberId") Long memberId,
            @RequestParam("itemId") Long bookId,
            @RequestParam("count") int count
    )
    {
        Long id=orderService.order(memberId, bookId, count);

        return "redirect:/";
    }
}
