CREATE TABLE customer (
    customer_id       MEDIUMINT NOT NULL AUTO_INCREMENT,
    name              VARCHAR(255),
    address           VARCHAR(255),
    email             VARCHAR(255),
    PRIMARY KEY (customer_id)
);

ALTER TABLE customer AUTO_INCREMENT = 1000;