#Criando a tabela de cliente
CREATE TABLE IF NOT EXISTS customer (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'identificador do cliente',
    name VARCHAR(100) NOT NULL COMMENT 'Nome do cliente',
    email VARCHAR(255) NOT NULL COMMENT 'Email do cliente',
    PRIMARY KEY (id)
);

#Comentando as colunas
ALTER TABLE customer COMMENT 'Tabela de cliente';
