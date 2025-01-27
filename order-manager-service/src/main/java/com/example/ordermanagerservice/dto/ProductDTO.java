package com.example.ordermanagerservice.dto;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductDTO {

    private UUID id;

    @NotNull(message = "Название продукта не должно быть пустым.")
    private String productName;

    @NotNull(message = "Цена не может быть пустой")
    @DecimalMin(value = "0.01", inclusive = true, message = "Цена должна быть больше 0")
    private BigDecimal price;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
