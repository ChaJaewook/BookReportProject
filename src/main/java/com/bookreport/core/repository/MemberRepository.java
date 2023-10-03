package com.bookreport.core.repository;


import com.bookreport.core.domain.Member;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static com.bookreport.core.domain.QMember.member;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;
    @Autowired
    private final JPAQueryFactory jpaQueryFactory;

    public void save(Member member)
    {
        em.persist(member);
    }

    public Member findOne(Long id)
    {
        return em.find(Member.class, id);
    }

    public List<Member> findAll()
    {
        //모두 검색
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name)
    {
        return em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

    public Long delete(Member member)
    {
        em.remove(member);
        return member.getId();
    }

    public QueryResults<Member> memberListSearch(String type, String card, int offset)
    {
        QueryResults<Member> results=jpaQueryFactory
                .select(member)
                .where(memberListSearchCondition(type, card))
                .offset(offset)
                .limit(10)
                .fetchResults();

        return results;
    }

    private BooleanExpression memberListSearchCondition(String type, String card)
    {
        switch(type)
        {
            case "name":
                return member.name.contains(card);
            default:
                return member.name.contains("");
        }
    }
}
