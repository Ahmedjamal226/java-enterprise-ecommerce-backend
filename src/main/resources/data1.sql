-- Batch 1: Initial 3 vehicles with image byte placeholders
INSERT INTO product (brand, stock_quantity, category, description, name, price, product_available, release_date, image_name, image_type, image_data) VALUES 
('Tata', 45, 'Cars', 'A compact SUV with a 5-star GNCAP safety rating and bold design.', 'Tata Punch', 650000.00, true, '2026-01-15', 'tata-punch.jpg', 'image/jpeg', X'00'),
('Maruti Suzuki', 80, 'Cars', 'The premium, feature-loaded sedan offering exceptional fuel efficiency.', 'Maruti Dzire', 750000.00, true, '2026-03-10', 'all-cars.jpg', 'image/jpeg', X'00'),
('Hyundai', 35, 'Cars', 'An ultimate mid-size SUV packed with cutting-edge tech and comfort.', 'Hyundai Creta', 1100000.00, true, '2026-02-20', 'images.jpg', 'image/jpeg', X'00');

-- Batch 2: Additional vehicles (Explicitly adding NULL placeholders for the image columns to keep data uniform)
INSERT INTO product (brand, stock_quantity, category, description, name, price, product_available, release_date, image_name, image_type, image_data) VALUES
('Mahindra', 30, 'Cars', 'A rugged off-road SUV with a powerful engine.', 'Mahindra Thar', 1200000.00, true, '2024-04-01', null, null, null),
('Honda', 60, 'Cars', 'A premium sedan with a sleek design.', 'Honda City', 1100000.00, true, '2024-05-01', null, null, null);