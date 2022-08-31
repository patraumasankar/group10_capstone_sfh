USE sfh_db;

CREATE TABLE cart (
    cart_id BIGINT NOT NULL AUTO_INCREMENT,
    quantity INTEGER NOT NULL,
    status INTEGER NOT NULL,
    total_price BIGINT NOT NULL,
    product_id BIGINT,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (cart_id)
);
CREATE TABLE main_order (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date DATE,
    status VARCHAR(255),
    total_price BIGINT NOT NULL,
    user_email VARCHAR(255),
    user_firstname VARCHAR(255),
    user_lastname VARCHAR(255),
    user_phone BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    category VARCHAR(255),
    product_description VARCHAR(255),
    product_name VARCHAR(255),
    product_price BIGINT NOT NULL,
    product_stock INTEGER NOT NULL,
    url VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    phone BIGINT NOT NULL,
    role VARCHAR(255),
    username VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE wishlist (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE cart 
       ADD CONSTRAINT FK3d704slv66tw6x5hmbm6p2x3u 
       FOREIGN KEY (product_id) 
       REFERENCES product (id);
ALTER TABLE cart 
       ADD CONSTRAINT FKl70asp4l4w0jmbm1tqyofho4o 
       FOREIGN KEY (user_id) 
       REFERENCES user (id);
ALTER TABLE main_order 
       ADD CONSTRAINT FK5ybfohtl5kmcce8h7v14mrsr1 
       FOREIGN KEY (user_id) 
       REFERENCES user (id);
ALTER TABLE wishlist 
       ADD CONSTRAINT FKqchevbfw5wq0f4uqacns02rp7 
       FOREIGN KEY (product_id) 
       REFERENCES product (id);
       
ALTER TABLE wishlist 
       ADD CONSTRAINT FKd4r80jm8s41fgoa0xv9yy5lo8 
       FOREIGN KEY (user_id) 
       REFERENCES user (id);
       
CREATE TABLE discount (
    id BIGINT NOT NULL AUTO_INCREMENT,
    coupon_name VARCHAR(255),
    username VARCHAR(255),
    value BIGINT,
    PRIMARY KEY (id)
);