-- V1__create_table_image.sql
CREATE TABLE image (
                       id BIGSERIAL PRIMARY KEY,
                       photo_url VARCHAR(255) NOT NULL,
                       date TIMESTAMP NOT NULL,
                       latitude VARCHAR(255),
                       longitude VARCHAR(255),
                       profil_id BIGINT,  -- Clé étrangère vers la table User
                       CONSTRAINT fk_profil FOREIGN KEY (profil_id) REFERENCES profil (id) ON DELETE SET NULL
);
