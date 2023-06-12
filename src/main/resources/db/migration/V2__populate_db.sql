INSERT INTO client (name) VALUES
('Max Pain'),
('Jango Kek'),
('Optimus Priem'),
('Boris Bruda'),
('Arnold Hey'),
('Nicolas Cgae'),
('John Snow'),
('Kandruk Lambar'),
('Tony Versetti'),
('Scotty Doesntknow');

INSERT INTO planet (id, name) VALUES
('MCY1', 'Mercury'),
('VNS2', 'Venus'),
('ERH3', 'Earth'),
('MRS4', 'Mars'),
('JTR5', 'Jupiter');

INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES
(1, 'MCY1', 'MRS4'),
(2, 'ERH3', 'MRS4'),
(3, 'VNS2', 'ERH3'),
(4, 'VNS2', 'MRS4'),
(5, 'MCY1', 'MRS4'),
(6, 'JTR5', 'MRS4'),
(7, 'JTR5', 'ERH3'),
(8, 'MRS4', 'VNS2'),
(9, 'VNS2', 'JTR5'),
(10, 'MCY1', 'ERH3');