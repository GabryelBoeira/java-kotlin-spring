CREATE TABLE purchase
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    nfe         VARCHAR(255)          NULL,
    price       DECIMAL               NULL,
    created_at  datetime              NULL,
    customer_id BIGINT                NULL,
    PRIMARY KEY (id)
);

ALTER TABLE purchase
    ADD CONSTRAINT FK_PURCHASE_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

CREATE TABLE purchase_book
(
    book_id     BIGINT NOT NULL,
    purchase_id BIGINT NOT NULL,
    primary key (book_id, purchase_id)
);

ALTER TABLE purchase_book
    ADD CONSTRAINT fk_purboo_on_book_model FOREIGN KEY (book_id) REFERENCES book (id);
ALTER TABLE purchase_book
    ADD CONSTRAINT fk_purboo_on_purchase_model FOREIGN KEY (purchase_id) REFERENCES purchase (id);
