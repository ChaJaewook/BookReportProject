package com.bookreport.core.repository;

import com.bookreport.core.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Book book)
    {
        em.persist(book);
    }

    public Book findOne(Long id){
        return em.find(Book.class,id);
    }

    public List<Book> findAll()
    {
        return em.createQuery("select m from Book m", Book.class)
                .getResultList();
    }

    public List<Book> findByTitle(String title)
    {
        return em.createQuery("select m from Book m where title=:title",Book.class)
                .setParameter("title",title)
                .getResultList();
    }

    public Long delete(Book book)
    {
        em.remove(book);
        return book.getId();
    }
}
