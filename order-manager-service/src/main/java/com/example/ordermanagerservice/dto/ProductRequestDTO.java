package com.example.ordermanagerservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public class ProductRequestDTO {

    @NotNull(message = "Customer name can't be null")
    private String customerName;
    @Size(min = 1, message = "At least one product ID must be provided")
    private List<UUID> productIds;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<UUID> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<UUID> productIds) {
        this.productIds = productIds;
    }
}
