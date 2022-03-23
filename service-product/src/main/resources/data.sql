INSERT INTO springcloud_db.categories (id, name) VALUES (1, 'shoes');
INSERT INTO springcloud_db.categories (id, name) VALUES (2, 'books');
INSERT INTO springcloud_db.categories (id, name) VALUES (3, 'electronics');

INSERT INTO springcloud_db.products (id, name, code, description, stock,price,status, create_at,category_id)
VALUES (1, 'adidas Cloudfoam Ultimate', '001', 'Walk in the air in the black / black CLOUDFOAM ULTIMATE running shoe from ADIDAS',5,178.89,'CREATED','2021-09-05',1);

INSERT INTO springcloud_db.products (id, name, code, description, stock,price,status, create_at,category_id)
VALUES (2, 'nIKE Cloudfoam Ultimate', '001', 'Walk in the air in the black / black CLOUDFOAM ULTIMATE running shoe from NIKE',5,178.89,'CREATED','2021-09-05',1);

INSERT INTO springcloud_db.products (id, name, code, description, stock,price,status, create_at,category_id)
VALUES (3, 'PUMA Cloudfoam Ultimate', '001', 'Walk in the air in the black / black CLOUDFOAM ULTIMATE running shoe from PUMA',5,178.89,'CREATED','2021-09-05',1);

