#Adicionando nova coluna
ALTER TABLE customer ADD COLUMN status VARCHAR(20) NOT NULL COMMENT 'Status para o cadastro do cliente Active -activo, Inactive - inativo';