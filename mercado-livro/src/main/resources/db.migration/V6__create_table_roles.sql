create table roles (
    customer_id bigint       not null comment 'Id do usuário',
    role        varchar(50) not null comment 'Papel do usuário'
);

ALTER TABLE roles
    ADD CONSTRAINT fk_roles_on_customer FOREIGN KEY (customer_id) REFERENCES customer (id);

