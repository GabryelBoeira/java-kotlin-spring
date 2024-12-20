#Criando a tabela de book
CREATE TABLE IF NOT EXISTS book (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'identificador do livro',
    name VARCHAR(100) NOT NULL COMMENT 'Nome do livro',
    price decimal(10,2) NOT NULL COMMENT 'Preço do livro',
    customer_id BIGINT NOT NULL COMMENT 'Identificador do cliente',
    status VARCHAR(20) NOT NULL COMMENT 'Status do livro',
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);
