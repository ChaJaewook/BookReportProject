package com.bookreport.core.repository;

import com.bookreport.core.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import static com.bookreport.core.domain.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public List<Order> findAll(){
        return em.createQuery("select o from Order o",Order.class).getResultList();
    }

    public Order findById(Long id)
    {
        return em.find(Order.class, id);
    }

    public Long save(Order order){
        em.persist(order);
        return order.getId();
    }

    public List<Order> searchOrder()
    {
        return null;
    }
}
