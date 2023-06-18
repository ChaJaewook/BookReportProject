package com.bookreport.core.repository;

import com.bookreport.core.domain.BookReport;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookReportRepository {
    @PersistenceContext
    EntityManager em;

    public void save(BookReport bookReport)
    {
        System.out.println(bookReport.toString());
        em.persist(bookReport);
    }

    public BookReport findOne(Long id)
    {
        return em.find(BookReport.class , id);
    }

    public List<BookReport> findAll()
    {
        return em.createQuery("select m from BookReport m")
                .getResultList();
    }

    public List<BookReport> findByTitle(String book_title)
    {
        return em.createQuery("select m from BookReport m where book_title=:book_title")
                .setParameter("book_title",book_title)
                .getResultList();
    }

    public List<BookReport> findByMemberId(Long memberId)
    {
        //검색로직
        return em.createQuery("select m from BookReport m join m.member o"+
                                " where o.id=:memberid",
                        BookReport.class)
                .setParameter("memberid", memberId)
                .getResultList();

        /*return em.createQuery("select m from BookReport m join m.member o",
                        BookReport.class)
                .getResultList();*/
        //order와 member를 join
    }

}
