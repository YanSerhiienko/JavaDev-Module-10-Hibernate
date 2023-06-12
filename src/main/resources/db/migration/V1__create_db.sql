CREATE TABLE client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    CONSTRAINT client_name_length CHECK ((CHAR_LENGTH(name) >=2) AND (CHAR_LENGTH(name) <=200))
);

CREATE TABLE planet (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(500) NOT NULL,
    CONSTRAINT planet_name_length CHECK((CHAR_LENGTH(name) >=1) AND (CHAR_LENGTH(name) <=500)),
    CONSTRAINT planet_id_chars CHECK(REGEXP_LIKE(id, '\b[A-Z0-9]+\b'))
);

CREATE TABLE ticket (
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    client_id BIGINT NOT NULL,
    from_planet_id VARCHAR,
    to_planet_id VARCHAR,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (from_planet_id) REFERENCES planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES planet(id)
)

