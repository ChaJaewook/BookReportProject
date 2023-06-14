package com.bookreport.core.controller;

import com.bookreport.core.domain.Book;
import com.bookreport.core.domain.Member;
import com.bookreport.core.service.BookReportService;
import com.bookreport.core.service.BookService;
import com.bookreport.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookReportController {
    private final BookReportService bookReportService;
    private final BookService bookService;
    private final MemberService memberService;

    @GetMapping("/bookreport")
    public String createForm(Model model)
    {
        List<Member> members=memberService.findMembers();
        List<Book> books=bookService.findBooks();

        model.addAttribute("members",members);
        model.addAttribute("books",books);
        model.addAttribute("bookReportForm",new BookReportForm());

        return "bookreports/bookReportForm";
    }

}
