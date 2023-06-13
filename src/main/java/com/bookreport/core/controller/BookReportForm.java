package com.bookreport.core.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookReportForm {

    private Long bookReportId;
    private String bookTitle;
    private String content;
    private String bookId;
    private String memberId;

}
