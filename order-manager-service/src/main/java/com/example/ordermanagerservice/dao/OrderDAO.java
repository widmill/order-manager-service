package com.example.ordermanagerservice.dao;

import com.example.ordermanagerservice.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDAO {

    private final SessionFactory sessionFactory;

    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();

        session.save(order);
    }

    @Transactional
    public List<Order> findOrdersByCustomerName(String customerName) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Order WHERE customerName = :customerName", Order.class)
                .setParameter("customerName", customerName)
                .list();
    }

    @Transactional
    public List<Order> findAllOrders() {
        Session session = sessionFactory.getCurrentSession();

        return session.
                createQuery("FROM Order", Order.class).list();
    }
}
