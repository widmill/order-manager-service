package com.example.ordermanagerservice.controller;

import com.example.ordermanagerservice.dto.OrderDTO;
import com.example.ordermanagerservice.dto.ProductRequestDTO;
import com.example.ordermanagerservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Метод сохраняет заказ в базу данных.
     *
     * @param productRequestDTO тело запроса состоит из имени клиента и списка продуктов в заказ.
     * @return Возвращает заказ с именем клиента и списком продуктов.
     */
    @PostMapping
    public OrderDTO saveOrder(@RequestBody ProductRequestDTO productRequestDTO) {
       return orderService.saveOrderForApi(productRequestDTO.getCustomerName(),
                productRequestDTO.getProductIds());
    }

    /**
     * Метод для поиска заказов через имя клиента.
     *
     * @param customerName имя клиента.
     * @return Возвращает все заказы на имя клиента.
     */
    @GetMapping
    public List<OrderDTO> findByCustomerName(@RequestParam String customerName) {

        return orderService.findOrdersByCustomerName(customerName);

    }
}
