INSERT INTO brand (name, country) VALUES ('NorthPoint Athletics', 'Canada');
INSERT INTO brand (name, country) VALUES ('TerraGear', 'USA');
INSERT INTO brand (name, country) VALUES ('Aetheros Tech', 'Japan');
INSERT INTO brand (name, country) VALUES ('Logitech', 'Switzerland');
INSERT INTO brand (name, country) VALUES ('Razer', 'USA');

INSERT INTO items (name, sku, price, brand_id, created_at)
VALUES ('Gaming Mouse', 'GM-001', 49.99, 1, CURRENT_TIMESTAMP);

INSERT INTO items (name, sku, price, brand_id, created_at)
VALUES ('Mechanical Keyboard', 'MK-001', 129.99, 2, CURRENT_TIMESTAMP);


INSERT INTO items (name, sku, price, brand_id, created_at) 
VALUES ('Running Shoes', 'NP-SH-001', 89.99, 1, CURRENT_TIMESTAMP);

INSERT INTO items (name, sku, price, brand_id, created_at) 
VALUES ('Hiking Backpack', 'TG-BP-502', 120.00, 2, CURRENT_TIMESTAMP);

INSERT INTO items (name, sku, price, brand_id, created_at) 
VALUES ('Smart Watch', 'AT-W-99', 249.50, 3, CURRENT_TIMESTAMP);

INSERT INTO items (name, sku, price, brand_id, created_at) 
VALUES ('Yoga Mat', 'NP-YM-010', 35.00, 1, CURRENT_TIMESTAMP);