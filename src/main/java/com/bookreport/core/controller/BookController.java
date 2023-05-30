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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        DateTimeFormatter DATEFORMATTER=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld=LocalDate.parse(form.getPublish_date(),DATEFORMATTER);
        LocalDateTime ldt=LocalDateTime.of(ld,LocalDateTime.now().toLocalTime());


        double bookGrade=0;
        switch(form.getGrade())
        {
            case "BSET":
                bookGrade=5.0;
                break;
            case "BETTER":
                bookGrade=4.0;
                break;
            case "GOOD":
                bookGrade=3.0;
                break;
            case "NORMAL":
                bookGrade=2.0;
                break;
            case "BAD":
                bookGrade=1.0;
                break;
            default:
                break;
        }

        book.setTitle(form.getTitle());
        book.setAuthor(form.getAuthor());
        book.setGrade(bookGrade);
        book.setIsbn(form.getIsbn());
        book.setPublish_date(ldt);

        bookRepository.save(book);
        System.out.println("성공");
        return "redirect:/";
    }

    @GetMapping("/books")
    public String list(Model model)
    {
        List<Book> books=new ArrayList<>();
        books=bookRepository.findAll();
        model.addAttribute("books",books);

        return "books/bookList";
    }
}
