package com.bookreport.core.controller;

import com.bookreport.core.domain.Book;
import com.bookreport.core.domain.BookCategory;
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

        String oriImgName=form.getImgFile().getOriginalFilename();
        String imgName="";

        String projectPath=System.getProperty("user.dir")+"/src/main/resources/static/img/";
        UUID uuid= UUID.randomUUID();
        String savedFileName=uuid+"_"+oriImgName;
        imgName=savedFileName;
        File saveFile=new File(projectPath,imgName);
        form.getImgFile().transferTo(saveFile);


        Book book=Book.builder()
                .title(form.getTitle())
                .author(form.getAuthor())
                .grade(form.getGrade())
                .isbn(form.getIsbn())
                .imgPath("/img/"+imgName)
                .imgName(imgName)
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .publishDate(form.getPublishDate())
                .bookCategory(form.getCategory())
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
        Book book=bookService.findOne(bookId);

        BookForm bookForm=new BookForm();
        bookForm.setId(book.getId());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setIsbn(book.getIsbn());
        bookForm.setGrade(book.getGrade());
        bookForm.setCategory(book.getBookCategory());
        bookForm.setPublishDate(book.getPublishDate());
        bookForm.setPrice(book.getPrice());
        bookForm.setStockQuantity(book.getStockQuantity());
        bookForm.setTitle(book.getTitle());


        model.addAttribute("form",bookForm);
        return "books/updateBookForm";
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
