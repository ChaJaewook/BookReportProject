package com.bookreport.core.service;

import com.bookreport.core.domain.Book;
import com.bookreport.core.domain.BookReport;
import com.bookreport.core.domain.Member;
import com.bookreport.core.repository.BookReportRepository;
import com.bookreport.core.repository.BookRepository;
import com.bookreport.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookReportService {
    private final BookReportRepository bookReportRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    @Transactional
    public Long save(BookReport bookReport)
    {
        bookReportRepository.save(bookReport);
        return bookReport.getId();
    }

    public BookReport findOne(Long book_report_id){
        return bookReportRepository.findOne(book_report_id);
    }

    public List<BookReport> searchMember(Long memberId)
    {
        return bookReportRepository.findByMemberId(memberId);
    }

    public List<BookReport> findBookReports()
    {
        return bookReportRepository.findAll();
    }

    @Transactional
    public Long write(Long memberId, Long bookId, String content)
    {
        //엔티티 조회
        Member member=memberRepository.findOne(memberId);
        Book book=bookRepository.findOne(bookId);

        // BookReport 생성
        BookReport bookReport=BookReport.createBookReport(member,book,content);
        bookReportRepository.save(bookReport);
        return bookReport.getId();
    }


}
