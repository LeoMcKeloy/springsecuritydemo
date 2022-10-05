CREATE USER leo WITH PASSWORD 'leo' CREATEDB;
CREATE DATABASE springSecurityDemoDB
    WITH
    OWNER = springsecuritydemo
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE IF NOT EXISTS users (
                                       id BIGINT PRIMARY KEY,
                                       email VARCHAR(255) NOT NULL UNIQUE,
                                       first_name VARCHAR(50) NOT NULL,
                                       last_name VARCHAR(100) NOT NULL,
                                       password VARCHAR(255) NOT NULL,
                                       role VARCHAR(20) NOT NULL DEFAULT 'USER',
                                       status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
    );