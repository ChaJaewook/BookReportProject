package com.bookreport.core.repository;

import com.bookreport.core.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookRepository {

    @PersistenceContext
    EntityManager em;

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
}
