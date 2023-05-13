package com.bookreport.core.service;

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
    public Long save(Book book){
        bookRepository.save(book);
        return book.getId();
    }

    public List<Book> findBooks(){
        return bookRepository.findAll();
    }

    public Book findOne(Long book_id)
    {
        return bookRepository.findOne(book_id);
    }


}
