package com.example.ordermanagerservice.dao;

import com.example.ordermanagerservice.entity.Product;
import com.example.ordermanagerservice.exception.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductDAO {

    private final SessionFactory sessionFactory;

    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Product saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();

        session.save(product);
        return product;
    }

    @Transactional
    public List<Product> findAllById(List<UUID> ids) {
        Session session = sessionFactory.getCurrentSession();

        List<Product> products = new ArrayList<>();
        for (UUID id : ids) {
            Product product = session.get(Product.class, id);
            if (product == null) {
                throw new EntityNotFoundException("Заказ с данным ID не найден.");
            }
            products.add(product);
        }

        return products;
    }

    @Transactional
    public Product findProductById(UUID productId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Product.class, productId);
    }
}
