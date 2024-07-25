


CREATE TABLE customer
(
    id      VARCHAR(15) PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    address VARCHAR(500) NOT NULL
);

CREATE TABLE customer_contact
(
    number VARCHAR(15) PRIMARY KEY,
    customer_id VARCHAR(15) NOT NULL ,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE item(
    code VARCHAR(50) PRIMARY KEY ,
    name VARCHAR(100) NOT NULL ,
    stock INT NOT NULL ,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE user(
    user_name VARCHAR(100) PRIMARY KEY ,
    name VARCHAR(200) NOT NULL ,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE `order`(
    id VARCHAR(15) PRIMARY KEY ,
    date DATE NOT NULL ,
    customer_id VARCHAR(15) NOT NULL ,
    user_name VARCHAR(100) NOT NULL ,
    CONSTRAINT fk_customer_order FOREIGN KEY (customer_id) REFERENCES customer(id),
    CONSTRAINT fk_user FOREIGN KEY (user_name) REFERENCES user(user_name)
);

CREATE TABLE order_item(
    order_id VARCHAR(15) NOT NULL ,
    item_code VARCHAR(50) NOT NULL ,
    CONSTRAINT pk_order_item PRIMARY KEY (order_id,item_code),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES `order`(id),
    CONSTRAINT fk_item FOREIGN KEY (item_code) REFERENCES item(code),
    price DECIMAL NOT NULL ,
    discount INT NOT NULL

);