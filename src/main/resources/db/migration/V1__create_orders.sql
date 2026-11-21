CREATE TABLE orders (
    id             BIGSERIAL PRIMARY KEY,
    customer_email VARCHAR(255)   NOT NULL,
    status         VARCHAR(20)    NOT NULL,
    total          NUMERIC(10, 2) NOT NULL,
    created_at     TIMESTAMPTZ    NOT NULL
);