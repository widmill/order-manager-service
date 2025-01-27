package com.example.ordermanagerservice.util;

import com.example.ordermanagerservice.config.DataSourceConfig;
import com.example.ordermanagerservice.dto.OrderDTO;
import com.example.ordermanagerservice.entity.Order;
import com.example.ordermanagerservice.entity.Product;
import com.example.ordermanagerservice.service.OrderService;
import com.example.ordermanagerservice.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class ConsoleMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startConsole(ApplicationContext context) {
        ProductService productService = context.getBean(ProductService.class);
        OrderService orderService = context.getBean(OrderService.class);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Добавить продукт");
            System.out.println("2 - Создать заказ");
            System.out.println("3 - Вывести заказы по имени клиента");
            System.out.println("4 - Экспортировать заказы в JSON");
            System.out.println("5 - Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    addProduct(productService);
                    break;
                case 2:
                    createOrder(productService, orderService);
                    break;
                case 3:
                    getOrdersByCustomerName(orderService);
                    break;
                case 4:
                    exportOrdersToJson(orderService);
                    break;
                case 5:
                    System.out.println("Выход из программы...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
    }

    private static void addProduct(ProductService productService) {
        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();

        System.out.print("Введите цену продукта: ");
        BigDecimal price = scanner.nextBigDecimal();

        scanner.nextLine(); // очистка буфера после BigDecimal
        Product product = new Product(null, name, price);
        productService.saveProduct(product);
        System.out.println("Продукт успешно добавлен.");
    }

    private static void createOrder(ProductService productService, OrderService orderService) {
        System.out.print("Введите имя клиента: ");
        String customerName = scanner.nextLine();

        System.out.println("Введите ID продуктов через запятую : ");
        String productIds = scanner.nextLine();

        List<Product> productDTOs = List.of(productIds.split(",")).stream()
                .map(id -> {
                    UUID uuid = UUID.fromString(id.trim());
                    return productService.findProductById(uuid);
                }).collect(Collectors.toList());

        BigDecimal totalPrice = productDTOs.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order(null, customerName, productDTOs, totalPrice);
        orderService.saveOrderForConsoleMenu(order);
        System.out.println("Заказ успешно создан.");
    }

    private static void getOrdersByCustomerName(OrderService orderService) {
        System.out.print("Введите имя клиента для поиска заказов: ");
        String customerName = scanner.nextLine();

        List<OrderDTO> orders = orderService.findOrdersByCustomerName(customerName);
        if (orders.isEmpty()) {
            System.out.println("Заказы не найдены для клиента: " + customerName);
        } else {
            orders.forEach(order -> {
                System.out.println("Заказ ID: " + order.getId());
                System.out.println("Общая стоимость: " + order.getTotalPrice());
                System.out.println("Продукты: " + order.getProducts());
                System.out.println();
            });
        }
    }

    private static void exportOrdersToJson(OrderService orderService) {
        System.out.print("Введите название для файла экспорта: ");
        String filename = scanner.nextLine();

        orderService.ordersExportToJsonFile(filename);
        System.out.println("Экспорт заказов в JSON - функция не реализована.");
    }

}