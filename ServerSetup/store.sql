CREATE TABLE product_catalog (
  upc VARCHAR(6) NOT NULL,
  given_name VARCHAR(45) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (upc)
);

INSERT INTO product_catalog (upc, given_name, price) VALUES 
('0001', 'AVOCADOS', 1.25),
('0002', 'APPLES, RED DELICIOUS', 1.00),
('0003', 'CARROTS', 1.48),
('0004', 'BANANAS', 1.38),
('0005', 'MILK, 2%', 2.21),
('0006', 'SALAD MIX', 1.78),
('0007', 'ALUMINUM FOIL', 8.10),
('0008', 'BROCCOLI', 1.62),
('0009', 'TOMATOES', 2.49),
('0010', 'ROTISSERIE CHICKEN', 11.39);

CREATE TABLE transactions (
  customer_name VARCHAR(45) NOT NULL,
  transaction_id INT NOT NULL,
  payment_type VARCHAR(45) NOT NULL,
  given_date DATE NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (transaction_id)
);

INSERT INTO transactions VALUES 
('Jim',5,'bill','2008-11-11',4.5);

CREATE TABLE transaction_lines (
  upc VARCHAR(6) NOT NULL,
  transaction_id INT NOT NULL,
  quantity INT NOT NULL,
  subtotal DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (transaction_id),
  FOREIGN KEY (upc) REFERENCES product_catalog (upc)
);

INSERT INTO transaction_lines VALUES 
('0001',5,6, 20.0);