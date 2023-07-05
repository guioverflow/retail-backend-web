

-- Criação da tabela de estabelecimento
Create table Establishments (
	IdEstablishment SERIAL PRIMARY KEY,
	Phone bigint,
	CEP int
);

-- Criação da tabela produtos
CREATE TABLE Products (
	IdProduct SERIAL PRIMARY KEY,
    GTIN bigint NOT NULL,
    Name varchar(200) NOT NULL
);

-- Criação da tabela de estoque
CREATE TABLE Stock (
    IdEstablishment int NOT NULL,
    IdProduct int NOT NULL,
    Quantity int NOT NULL,
    PRIMARY KEY (IdEstablishment, IdProduct),
    FOREIGN KEY (IdEstablishment) REFERENCES Establishments (IdEstablishment),
    FOREIGN KEY (IdProduct) REFERENCES Products (IdProduct)
);

-- Criação da tabela usuários
CREATE TABLE Users (
    IdUser SERIAL PRIMARY KEY,
	IdEstablishment int NOT NULL,
	UserRole varchar(100) NOT NULL,
	DisplayName varchar(100) NOT NULL,
    Username varchar(100) NOT NULL,
    Password varchar(200) NOT NULL,
	FOREIGN KEY (IdEstablishment) REFERENCES Establishments (IdEstablishment)
);

-- Criação da tabela de vendas
CREATE TABLE Sales (
    IdSale SERIAL PRIMARY KEY,
    SaleDate date NOT NULL,
    IdUser int NOT NULL,
    FOREIGN KEY (IdUser) REFERENCES Users (IdUser)
);

-- Criação da tabela de pedidos
CREATE TABLE Orders (
    IdOrder SERIAL PRIMARY KEY,
    OrderDate date NOT NULL,
    IdUser int NOT NULL,
	Approved bool,
	IdManager int,
    FOREIGN KEY (IdUser) REFERENCES Users (IdUser),
	FOREIGN KEY (IdManager) REFERENCES Users (IdUser)
);

-- Criação da tabela associativa entre pedidos e produtos
CREATE TABLE OrderProducts (
    IdOrder int NOT NULL,
    IdProduct int NOT NULL,
    Quantity int NOT NULL,
    Price decimal(10, 2) NOT NULL,
    PRIMARY KEY (IdOrder, IdProduct),
    FOREIGN KEY (IdOrder) REFERENCES Orders (IdOrder),
    FOREIGN KEY (IdProduct) REFERENCES Products (IdProduct)
);

-- Criação da tabela associativa entre vendas e produtos
CREATE TABLE SaleProducts (
    IdSale int NOT NULL,
    IdProduct int NOT NULL,
    Quantity int NOT NULL,
    Price decimal(10, 2) NOT NULL,
    PRIMARY KEY (IdSale, IdProduct),
    FOREIGN KEY (IdSale) REFERENCES Sales (IdSale),
    FOREIGN KEY (IdProduct) REFERENCES Products (IdProduct)
);


-- ////////////////// INSERTS

-- Inserção de dados na tabela de estabelecimentos
INSERT INTO Establishments (Phone, CEP) VALUES
(1122334455, 01234567),
(2233445566, 12345678),
(3344556677, 23456789),
(4455667788, 34567890),
(5566778899, 45678901);

-- Inserção de dados na tabela de produtos
INSERT INTO Products (GTIN, Name) VALUES
(7894561230001, 'Arroz Branco 5kg'),
(7894561230002, 'Feijão Carioca 1kg'),
(7894561230003, 'Açúcar Refinado 2kg'),
(7894561230004, 'Óleo de Soja 900ml'),
(7894561230005, 'Macarrão Espaguete 500g'),
(7894561230006, 'Leite Integral 1L'),
(7894561230007, 'Café Torrado e Moído 500g'),
(7894561230008, 'Farinha de Trigo 1kg'),
(7894561230009, 'Sal Refinado 1kg'),
(7894561230010, 'Molho de Tomate 340g'),
(7894561230011, 'Sabonete em Barra Neutro'),
(7894561230012, 'Detergente Líquido 500ml'),
(7894561230013, 'Papel Higiênico 30 rolos'),
(7894561230014, 'Shampoo 400ml'),
(7894561230015, 'Condicionador 400ml'),
(7894561230016, 'Desodorante Aerosol 150ml'),
(7894561230017, 'Creme Dental 90g'),
(7894561230018, 'Escova de Dentes'),
(7894561230019, 'Sabão em Pó 1kg'),
(7894561230020, 'Amaciante de Roupas 2L'),
(7894561230021, 'Limpador Multiuso 500ml'),
(7894561230022, 'Água Mineral 500ml'),
(7894561230023, 'Refrigerante Cola 2L'),
(7894561230024, 'Suco de Laranja 1L'),
(7894561230025, 'Biscoito de Chocolate 200g'),
(7894561230026, 'Salgadinho de Milho 150g'),
(7894561230027, 'Chá Mate 1L'),
(7894561230028, 'Manteiga 200g'),
(7894561230029, 'Presunto Fatiado 200g'),
(7894561230030, 'Queijo Mussarela 500g');

-- Inserção de dados na tabela associativa de estoque
INSERT INTO Stock (IdEstablishment, IdProduct, Quantity) VALUES
(1, 1, 100),
(1, 2, 200),
(1, 3, 150),
(2, 1, 50),
(2, 4, 80),
(2, 5, 120),
(3, 2, 100),
(3, 3, 200),
(3, 6, 150),
(4, 1, 80),
(4, 4, 100),
(4, 7, 90),
(5, 2, 150),
(5, 5, 100),
(5, 8, 120),
(1, 9, 200),
(2, 10, 150),
(3, 11, 100),
(4, 12, 80),
(1, 13, 110),
(2, 6, 80),
(2, 7, 100),
(2, 9, 150),
(3, 5, 200),
(4, 8, 120),
(4, 10, 180),
(5, 1, 100),
(5, 3, 130),
(5, 12, 70);

-- Inserção de dados na tabela de usuários / Ainda não está com dados úteis
INSERT INTO Users (IdEstablishment, DisplayName, UserRole, Username, Password) VALUES
(1, 'João Silva', '2', 'joao.silva', 'senha123'),
(1, 'Maria Santos', '3', 'maria.santos', '123456'),
(2, 'Carlos Ferreira', '4', 'carlos.ferreira', 'abc123'),
(2, 'Ana Costa', '4', 'ana.costa', 'senha456'),
(3, 'Pedro Oliveira', '3', 'pedro.oliveira', 'senha789'),
(3, 'Mariana Almeida', '4', 'mariana.almeida', 'senha321'),
(4, 'Lucas Rocha', '4', 'lucas.rocha', 'senha654'),
(4, 'Camila Gomes', '3', 'camila.gomes', 'senha987'),
(5, 'Gabriel Santos', '2', 'gabriel.santos', 'senha246'),
(5, 'Isabela Lima', '3', 'isabela.lima', 'senha135');

INSERT INTO Orders (OrderDate, IdUser, Approved, IdManager) VALUES
('2023-07-01', 1, true, 2),
('2023-07-01', 1, true, 2),
('2023-07-02', 3, true, 4),
('2023-07-02', 2, false, 4),
('2023-07-02', 4, false, 1),
('2023-07-02', 5, true, 3),
('2023-07-03', 2, true, 1),
('2023-07-03', 3, false, 4),
('2023-07-03', 5, true, 3),
('2023-07-04', 4, true, 2);

INSERT INTO Sales (SaleDate, IdUser) VALUES
('2023-07-01', 1),
('2023-07-01', 2),
('2023-07-02', 3),
('2023-07-02', 4),
('2023-07-02', 5),
('2023-07-03', 2),
('2023-07-03', 3),
('2023-07-03', 4),
('2023-07-03', 1),
('2023-07-04', 5);

INSERT INTO OrderProducts (IdProduct, IdOrder, Quantity, Price) VALUES
(1, 1, 2, 9.99),
(1, 3, 1, 19.99),
(2, 2, 3, 15.99),
(2, 4, 2, 7.99),
(3, 1, 5, 9.99),
(3, 2, 2, 15.99),
(4, 3, 1, 19.99),
(4, 4, 3, 7.99),
(5, 1, 2, 9.99),
(5, 3, 4, 19.99),
(6, 2, 1, 15.99),
(6, 4, 2, 7.99),
(7, 1, 3, 9.99),
(7, 3, 1, 19.99),
(8, 2, 2, 15.99),
(8, 4, 3, 7.99),
(9, 1, 4, 9.99),
(9, 3, 2, 19.99),
(10, 2, 1, 15.99),
(10, 4, 4, 7.99),
(11, 1, 3, 9.99),
(11, 3, 2, 19.99),
(12, 2, 4, 15.99),
(12, 4, 1, 7.99),
(13, 1, 2, 9.99),
(13, 3, 3, 19.99),
(14, 2, 1, 15.99),
(14, 4, 2, 7.99),
(15, 1, 4, 9.99),
(15, 3, 1, 19.99),
(16, 2, 3, 15.99),
(17, 1, 2, 9.99),
(17, 3, 4, 19.99),
(18, 2, 1, 15.99),
(18, 4, 3, 7.99),
(19, 1, 3, 9.99),
(19, 3, 2, 19.99),
(20, 2, 2, 15.99),
(20, 4, 4, 7.99);

INSERT INTO SaleProducts (IdProduct, IdSale, Quantity, Price) VALUES
(1, 1, 2, 9.99),
(1, 3, 1, 19.99),
(2, 2, 3, 15.99),
(2, 4, 2, 7.99),
(3, 1, 5, 9.99),
(3, 2, 2, 15.99),
(4, 3, 1, 19.99),
(4, 4, 3, 7.99),
(5, 1, 2, 9.99),
(5, 3, 4, 19.99),
(6, 2, 1, 15.99),
(6, 4, 2, 7.99),
(7, 1, 3, 9.99),
(7, 3, 1, 19.99),
(8, 2, 2, 15.99),
(8, 4, 3, 7.99),
(9, 1, 4, 9.99),
(9, 3, 2, 19.99),
(10, 2, 1, 15.99),
(10, 4, 4, 7.99),
(11, 1, 3, 9.99),
(11, 3, 2, 19.99),
(12, 2, 4, 15.99),
(12, 4, 1, 7.99),
(13, 1, 2, 9.99),
(13, 3, 3, 19.99),
(14, 2, 1, 15.99),
(14, 4, 2, 7.99),
(15, 1, 4, 9.99),
(15, 3, 1, 19.99),
(16, 2, 3, 15.99),
(17, 1, 2, 9.99),
(17, 3, 4, 19.99),
(18, 2, 1, 15.99),
(18, 4, 3, 7.99),
(19, 1, 3, 9.99),
(19, 3, 2, 19.99),
(20, 2, 2, 15.99),
(20, 4, 4, 7.99);
