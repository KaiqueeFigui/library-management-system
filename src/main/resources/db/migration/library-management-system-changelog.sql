-- liquibase formatted sql

-- changeset kaique.lucena:1
CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    last_access TIMESTAMP,
    role_id INT,
    FOREIGN KEY (role_id)
        REFERENCES role(id)
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(13),
    pages INT,
    published_at DATE,
    author VARCHAR(255),
    summary VARCHAR(5000),
    in_stock INT
);

CREATE TABLE "order" (
    id SERIAL PRIMARY KEY,
    book_id INT,
    user_id INT,
    ordered_at TIMESTAMP,
    returned_at TIMESTAMP,
    due_date DATE,
    FOREIGN KEY (book_id)
            REFERENCES book(id),
    FOREIGN KEY (user_id)
            REFERENCES "user"(id)
);

INSERT INTO role (description) VALUES
    ('ADMIN'), ('USER');