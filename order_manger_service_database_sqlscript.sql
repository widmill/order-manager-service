CREATE TABLE products
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_name VARCHAR        NOT NULL,
    price        DECIMAL(10, 2) NOT NULL
);

CREATE TABLE orders
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_name VARCHAR        NOT NULL,
    total_price   DECIMAL(10, 2) NOT NULL
);

CREATE TABLE order_products
(
    order_id   UUID REFERENCES orders (id) ON DELETE CASCADE,
    product_id UUID REFERENCES products (id) ON DELETE CASCADE,
    PRIMARY KEY (order_id, product_id)
);

INSERT INTO products (product_name, price)
VALUES ('Плита', 100.00),
       ('Телевизор', 150.00),
       ('Микроволновка', 200.00);

INSERT INTO orders (customer_name, total_price)
VALUES ('Петр Иванов', 250.00),
       ('Анна Петрова', 300.00);