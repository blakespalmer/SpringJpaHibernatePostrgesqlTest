CREATE TABLE parent (
    id serial PRIMARY KEY,
    name text
);

CREATE TABLE child (
    id serial PRIMARY KEY,
    name text,
    fk_parent integer NOT NULL,
    FOREIGN KEY (fk_parent) REFERENCES parent (id)
);