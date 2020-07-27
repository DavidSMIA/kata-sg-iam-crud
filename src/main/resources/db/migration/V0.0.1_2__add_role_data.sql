CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO role(id, code, description, created_at, last_updated_at)
VALUES (uuid_generate_v4(), 'DEV', 'developper', NOW(), NOW());

INSERT INTO role(id, code, description, created_at, last_updated_at)
VALUES (uuid_generate_v4(), 'PO', 'Product Owner', NOW(), NOW());

INSERT INTO role(id, code, description, created_at, last_updated_at)
VALUES (uuid_generate_v4(), 'MANAGER', 'Manager', NOW(), NOW());