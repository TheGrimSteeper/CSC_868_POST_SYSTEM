-- Creates the tables for the database

CREATE TABLE "Store"."product_catalog" (
  "upc" VARCHAR(4) NOT NULL,
  "name" VARCHAR(45) NOT NULL,
  "price" DECIMAL(8) NOT NULL,
  PRIMARY KEY ("upc"));


CREATE TABLE "Store"."transactions" (
  "transactionID" VARCHAR(4) NOT NULL,
  "paymentType" VARCHAR(45) NOT NULL,
  "date" DATE NOT NULL,
  "total" DECIMAL(8) NOT NULL,
  PRIMARY KEY ("transactionID"));


CREATE TABLE "Store"."transaction_lines" (
  "upc" VARCHAR(4) NOT NULL,
  "transactionID" VARCHAR(4) NOT NULL,
  "quantity" INT NOT NULL,
  PRIMARY KEY ("upc", "transactionID"));
  
  -- Populates the product catalog
  
  INSERT INTO "Store"."product_catalog" ("upc", "name", "price") 
	VALUES 
('0001', 'AVOCADOS', 0001.25),
('0002', 'APPLES, RED DELICIOUS', 0001.00),
('0003', 'CARROTS', 0001.48),
('0004', 'BANANAS', 0001.38),
('0005', 'MILK, 2%', 0002.21),
('0006', 'SALAD MIX', 0001.78),
('0007', 'ALUMINUM FOIL', 0008.10),
('0008', 'BROCCOLI', 0001.62),
('0009', 'TOMATOES', 0002.49),
('0010', 'ROTISSERIE CHICKEN', 0011.39);

-- For inserting into transactions
INSERT INTO "Store"."transactions" ("transactionID", "paymentType", "date", "total") 
	VALUES 
    ('1111', 'CASH', CURRENT_DATE, 45);
    
-- This statement returns all items from a transaction based on the transactionID    

SELECT "B"."upc", "name", "quantity" FROM "Store"."product_catalog" AS "A", "Store"."transaction_lines" AS "B","Store"."transactions" AS "C" WHERE "A"."upc" = "B"."upc" AND
"B"."transactionID" = "C"."transactionID" AND "B"."transactionID" = '1111';