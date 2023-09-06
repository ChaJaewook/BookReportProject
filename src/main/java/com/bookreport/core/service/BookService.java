package com.bookreport.core.service;

import com.bookreport.core.controller.BookForm;
import com.bookreport.core.domain.Book;
import com.bookreport.core.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
/*
    - 예상치 못한 엔티티 등록, 삭제, 변경 예방
    - 성능 최적화
    - CUD 작업이 동작하지 않고, 스냅샷 저장, 변경감지(DIRTY CHECK)의 작업을 수행하지 않아 성능 향상
 */
public class BookService {

    //RequiredArgsConstructor final 필수
    private final BookRepository bookRepository;

    @Transactional
    public Long saveBook(Book book){
        bookRepository.save(book);
        return book.getId();
    }

    @Transactional
    public void updateBook(Long bookId, BookForm param)
    {
        Book findBook=bookRepository.findOne(bookId);
        double grade=0;
        //점수 업데이트 체크 로직
        switch(param.getGrade())
        {
            case "BEST":
                grade=5.0;
                break;
            case "BETTER":
                grade=4.0;
                break;
            case "GOOD":
                grade=3.0;
                break;
            case "Normal":
                grade=2.0;
                break;
            case "BAD":
                grade=1.0;
                break;
        }

        //변경감지 진행
        findBook.updateBook(param.getAuthor(), param.getTitle(), grade);
    }

    @Transactional
    public void deleteBook(Long id)
    {
        Book findBook=bookRepository.findOne(id);
        bookRepository.delete(findBook);
    }

    public List<Book> findBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public Book findOne(Long book_id)
    {
        return bookRepository.findOne(book_id);
    }


}
