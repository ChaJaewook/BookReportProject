package com.bookreport.core.controller;

import com.bookreport.core.domain.Book;
import com.bookreport.core.domain.BookCategory;
import com.bookreport.core.repository.BookRepository;
import com.bookreport.core.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books/new")
    public String createForm(Model model){
        model.addAttribute("bookForm", new BookForm());
        return "/books/createBookForm";
    }

    @PostMapping("/books/new")
    public String create(@Valid BookForm form, BindingResult result) throws IOException {
        if(result.hasErrors())
        {
            return "/books/createBookForm";
        }

        //Book book=new Book();
        DateTimeFormatter dateF=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld=LocalDate.parse(form.getPublish_date(),dateF);
        LocalDateTime ldt=LocalDateTime.of(ld,LocalDateTime.now().toLocalTime());

        String oriImgName=form.getImgFile().getOriginalFilename();
        String imgName="";

        String projectPath=System.getProperty("user.dir")+"/src/main/resources/static/img/";
        UUID uuid= UUID.randomUUID();

        String savedFileName=uuid+"_"+oriImgName;

        imgName=savedFileName;

        File saveFile=new File(projectPath,imgName);

        form.getImgFile().transferTo(saveFile);



        double bookGrade=0;
        switch(form.getGrade())
        {
            case "BEST":
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

        BookCategory bookC=null;
        switch(form.getCategory())
        {
            case "COMIC":
                bookC=BookCategory.COMIC;
                break;
            case "HISTORY":
                bookC=BookCategory.HISTORY;
                break;
            case "DOCUMENTARY":
                bookC=BookCategory.DOCUMENTARY;
                break;
        }

        Book book=Book.builder()
                .title(form.getTitle())
                .author(form.getAuthor())
                .grade(bookGrade)
                .sold(form.getSold())
                .isbn(form.getIsbn())
                .imgPath("/img/"+imgName)
                .imgName(imgName)
                .publish_date(ldt)
                .build();

        bookService.saveBook(book);
        return "redirect:/";
    }

    @GetMapping("/books")
    public String list(Model model)
    {
        List<Book> books=bookService.findBooks();
        model.addAttribute("books",books);

        return "books/bookList";
    }

    @GetMapping("/books/{id}/edit")
    public String updateBookForm(@PathVariable("id") Long bookId, Model model)
    {
        Book book=(Book)bookService.findOne(bookId);

        BookForm bookForm=new BookForm();
        bookForm.setId(book.getId());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setSold(book.getSold());
        bookForm.setIsbn(book.getIsbn());

        String grade="";
        if(book.getGrade()==5.0)
            grade="BEST";
        else if(book.getGrade()==4.0)
            grade="BETTER";
        else if(book.getGrade()==3.0)
            grade="GOOD";
        else if(book.getGrade()==2.0)
            grade="NORMAL";
        else if(book.getGrade()==1.0)
            grade="BAD";
        bookForm.setGrade(grade);
        bookForm.setPublish_date(book.getPublish_date().toString());
        bookForm.setTitle(book.getTitle());


        model.addAttribute("form",bookForm);
        return "/books/updateBookForm";
    }

    @PostMapping("/books/{bookId}/edit")
    public String updateBook(@PathVariable("bookId") Long bookId,@ModelAttribute("form") BookForm form){
        bookService.updateBook(bookId,form);
        return "redirect:/books";
    }

    @GetMapping("/books/{bookId}/delete")
    public String deleteBook(@PathVariable("bookId") Long bookId)
    {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }

}
