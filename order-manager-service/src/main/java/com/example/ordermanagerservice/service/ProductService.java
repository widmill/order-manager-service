package com.example.ordermanagerservice.service;

import com.example.ordermanagerservice.dao.ProductDAO;
import com.example.ordermanagerservice.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductDAO productDAO;
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product saveProduct(Product product) {
        log.info("Saving product = {}", product);

        return productDAO.saveProduct(product);
    }

    public Product findProductById(UUID productId) {

        return productDAO.findProductById(productId);

    }


}
