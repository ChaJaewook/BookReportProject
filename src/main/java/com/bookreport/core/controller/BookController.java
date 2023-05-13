package com.bookreport.core.controller;

import com.bookreport.core.domain.Book;
import com.bookreport.core.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("/books/new")
    public String createForm(Model model){
        model.addAttribute("bookForm", new BookForm());
        return "/books/createBookForm";
    }

    @PostMapping("/books/new")
    public String create(@Valid BookForm form, BindingResult result){
        if(result.hasErrors())
        {
            return "/books/createBookForm";
        }

        Book book=new Book();

        book.setTitle(form.getTitle());
        book.setAuthor(form.getAuthor());
        book.setGrade(form.getGrade());
        book.setIsbn(form.getIsbn());
        book.setPublish_date(form.getPublish_date());

        bookRepository.save(book);
        return "redirect:/";
    }
}
