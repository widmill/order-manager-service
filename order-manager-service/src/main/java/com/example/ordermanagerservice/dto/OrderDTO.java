package com.example.ordermanagerservice.dto;

import com.example.ordermanagerservice.entity.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderDTO {

    private UUID id;

    @NotNull(message = "Имя клиента не должно быть пустым.")
    private String customerName;

    @NotEmpty(message = "Должен присутствовать хотя бы один продукт.")
    private List<ProductDTO> products;
    private BigDecimal totalPrice;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
