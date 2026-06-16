CREATE TABLE identification (
                                id BIGSERIAL PRIMARY KEY,                 -- Identifiant unique auto-incrémenté
                                image_id BIGINT,                          -- Référence à l'image
                                species_id BIGINT,                        -- Référence à l'espèce
                                confidence FLOAT,
                                identification_date TIMESTAMP NOT NULL,   -- Date d'identification
                                CONSTRAINT fk_image FOREIGN KEY (image_id) REFERENCES image(id),       -- Clé étrangère vers la table `image`
                                CONSTRAINT fk_species FOREIGN KEY (species_id) REFERENCES info_species(id) -- Clé étrangère vers la table `info_species`
);
