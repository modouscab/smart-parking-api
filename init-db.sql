-- Create role 'parking' if it doesn't exist
CREATE ROLE parking WITH LOGIN PASSWORD 'parking';

-- Ensure the role can create databases
ALTER ROLE parking CREATEDB;

-- Create database 'parkingdb' if it doesn't exist
SELECT 'CREATE DATABASE parkingdb'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'parkingdb')\gexec

-- Grant privileges to parking role
GRANT ALL PRIVILEGES ON DATABASE parkingdb TO parking;

-- Connect to the parkingdb database
\c parkingdb

-- Grant schema privileges
GRANT ALL PRIVILEGES ON SCHEMA public TO parking;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO parking;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO parking;
