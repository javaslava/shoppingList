create SCHEMA IF NOT EXISTS store DEFAULT CHARACTER SET utf8 ;
USE store;

create TABLE IF NOT EXISTS products (
  id_product BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  actualPrice DECIMAL(10,2) NOT NULL,
  description VARCHAR(50) NULL,
  discount DECIMAL(10,2) NULL,
  category VARCHAR(15) NOT NULL,
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE = InnoDB
AUTO_INCREMENT = 1001;

create TABLE IF NOT EXISTS shoppingCarts (
  id_cart BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  cartName VARCHAR(15) NOT NULL,
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE = InnoDB
AUTO_INCREMENT = 1001;

create TABLE IF NOT EXISTS cartsContent (
  id_cartContent BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  cart_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE = InnoDB
AUTO_INCREMENT = 1001;

alter table cartsContent
add CONSTRAINT fk_cartsContent_cart_id
FOREIGN KEY (cart_id) REFERENCES shoppingCarts (id_cart)
ON delete RESTRICT
ON update CASCADE;

alter table cartsContent
add INDEX indx_cartsContent_cart_id(cart_id);

alter table cartsContent
add CONSTRAINT fk_cartsContent_product_id
FOREIGN KEY (product_id) REFERENCES products (id_product)
ON delete CASCADE
ON update CASCADE;

alter table cartsContent
add INDEX indx_cartsContent_product_id(product_id);