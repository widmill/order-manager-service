package com.example.ordermanagerservice.service;

import com.example.ordermanagerservice.dao.OrderDAO;
import com.example.ordermanagerservice.dao.ProductDAO;
import com.example.ordermanagerservice.dto.OrderDTO;
import com.example.ordermanagerservice.dto.ProductDTO;
import com.example.ordermanagerservice.entity.Order;
import com.example.ordermanagerservice.entity.Product;
import com.example.ordermanagerservice.exception.CustomerNotFound;
import com.example.ordermanagerservice.util.JsonExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    public void ordersExportToJsonFile(String filename) {

        JsonExporter.exportOrdersToJson(orderDAO.findAllOrders(), filename);

    }

    public List<OrderDTO> findOrdersByCustomerName(String customerName) {
        List<Order> orders = orderDAO.findOrdersByCustomerName(customerName);

        if (orders.isEmpty()) {
            throw new CustomerNotFound("Заказов на данное имя не найдено.");
        }

        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO saveOrderForApi(String customerName, List<UUID> productIds) {
        log.info("Saving order = {}, {}", customerName, productIds);

        Order order = new Order();
        order.setCustomerName(customerName);

        List<Product> products = productDAO.findAllById(productIds);
        order.setProduct(products);

        BigDecimal totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);


        orderDAO.saveOrder(order);

        log.info("Order successfully saved = {}", order);

        return convertToDTO(order);
    }

    public void saveOrderForConsoleMenu(Order order) {
        orderDAO.saveOrder(order);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setProducts(order.getProduct().stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(product.getId());
                    productDTO.setProductName(product.getProductName());
                    productDTO.setPrice(product.getPrice());
                    return productDTO;
                })
                .collect(Collectors.toList()));
        return dto;
    }
}
