package com.example.ordermanagerservice.controller;

import com.example.ordermanagerservice.dto.ProductDTO;
import com.example.ordermanagerservice.entity.Product;
import com.example.ordermanagerservice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper mapper;

    public ProductController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    /**
     * Метод сохраняет продукт в базу данных.
     *
     * @param productDTO тело запроса состоит из имени продукта и стоимости.
     */
    @PostMapping
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return mapToDto(productService.saveProduct(mapToProduct(productDTO)));
    }

    private Product mapToProduct(ProductDTO dto) {
        return mapper.map(dto, Product.class);
    }

    private ProductDTO mapToDto(Product product) {
        return mapper.map(product, ProductDTO.class);
    }


}
