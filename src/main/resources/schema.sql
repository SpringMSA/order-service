CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        product_id VARCHAR(120) NOT NULL UNIQUE,
                        qty INTEGER NOT NULL,
                        unit_price INTEGER NOT NULL,
                        total_price INTEGER NOT NULL,
                        user_id VARCHAR(255) NOT NULL,
                        order_id VARCHAR(255) NOT NULL UNIQUE,
                        created_at DATE DEFAULT CURRENT_DATE NOT NULL
);
