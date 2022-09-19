CREATE TABLE IF NOT EXISTS engineType(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS engine(
     id SERIAL PRIMARY KEY,
     fuelType VARCHAR NOT NULL,
     volume DOUBLE,
     horsepower INT
);

CREATE TABLE IF NOT EXISTS transmissionType(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS transmission(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL,
     gears INT,
     drive VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS bodyType(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS bodyColor(
     id SERIAL PRIMARY KEY,
     colour VARCHAR NOT NULL UNIQUE,
     hex VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS body(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL,
     colour VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicle(
    id SERIAL PRIMARY KEY,
    brand VARCHAR NOT NULL,
    model VARCHAR NOT NULL,
    engine_id INT REFERENCES engine(id),
    transmission_id INT NOT NULL REFERENCES transmission(id),
    body_id INT REFERENCES body(id),
    mileage INT,
    owners_count INT
);

CREATE TABLE IF NOT EXISTS account(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    login VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    phone VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS ad(
    id SERIAL PRIMARY KEY,
    header VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    vehicle_id INT REFERENCES vehicle(id),
    is_sold BOOL,
    photo bytea NOT NULL,
    account_id INT REFERENCES account(id),
    created TIMESTAMP NOT NULL,
    price DOUBLE
);