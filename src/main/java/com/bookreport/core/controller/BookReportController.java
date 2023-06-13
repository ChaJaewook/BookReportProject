package com.bookreport.core.controller;

import com.bookreport.core.service.BookReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BookReportController {
    BookReportService bookReportService;

    @GetMapping("/bookreports/new")
    public String createForm(Model model)
    {
        model.addAttribute("bookreportForm",new BookReportForm());
        return "/bookreports/createBookReportForm";
    }

    @PostMapping("/bookreports/new")
    public String create(BookReportForm form, BindingResult result)
    {
        if(result.hasErrors()){
            return "bookreports/createBookReportForm";
        }


    }
}
