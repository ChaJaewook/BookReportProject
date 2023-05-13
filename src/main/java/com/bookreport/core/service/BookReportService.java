package com.bookreport.core.service;

import com.bookreport.core.domain.BookReport;
import com.bookreport.core.repository.BookReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookReportService {
    private final BookReportRepository bookReportRepository;

    @Transactional
    public Long save(BookReport bookReport)
    {
        bookReportRepository.save(bookReport);
        return bookReport.getId();
    }

    public BookReport findOne(Long book_report_id){
        return bookReportRepository.findOne(book_report_id);
    }

    public List<BookReport> findBookReports()
    {
        return bookReportRepository.findAll();
    }


}
