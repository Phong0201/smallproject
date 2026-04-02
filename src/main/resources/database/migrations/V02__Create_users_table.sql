CREATE TABLE users (
        id BIGSERIAL PRIMARY KEY,
        user_catalogue_id BIGINT,
        name VARCHAR(50) NOT NULL,
        email VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        phone VARCHAR(20) UNIQUE NOT NULL,
        address VARCHAR(255),
        image VARCHAR(255),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

        CONSTRAINT fk_user_catalogue_id
        FOREIGN KEY (user_catalogue_id)
        REFERENCES user_catalogues(id)
        ON DELETE CASCADE
);