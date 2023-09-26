package com.bookreport.core.repository;

import com.bookreport.core.domain.Book;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.bookreport.core.domain.QBook.book;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    @PersistenceContext
    private final EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

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

    public QueryResults<Book> search(String searchType, String searchWord, int offset)
    {
        QueryResults<Book> result=
                jpaQueryFactory
                        .selectFrom(book)
                        .where(searchBookCondition(searchType, searchWord))
                        .offset((offset-1)*10)
                        .limit(10)
                        .fetchResults();

        return result;
    }

    private BooleanExpression searchBookCondition(String searchType, String searchWord)
    {
        switch(searchType)
        {
            case "bookTitle":
                return book.title.contains(searchWord);
            case "bookAuthor":
                return book.author.contains(searchWord);
            default:
                return book.title.contains(searchWord).or(book.author.contains(searchWord));
        }
    }

    public Long delete(Book book)
    {
        em.remove(book);
        return book.getId();
    }
}
