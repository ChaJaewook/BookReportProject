package com.bookreport.core.service;

import com.bookreport.core.domain.Book;
import com.bookreport.core.repository.BookRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void 책추가()
    {
        //given
        Book book=new Book();
        book.setTitle("바람과 함께 사라지다.");

        //when
        Long saveId=bookService.saveBook(book);

        //then
        Assertions.assertEquals(book,bookRepository.findOne(saveId));
    }

}