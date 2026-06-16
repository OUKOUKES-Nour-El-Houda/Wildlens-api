--noinspection SqlResolveForFile
CREATE TABLE info_species (
                                            id BIGSERIAL PRIMARY KEY,
                                            especes VARCHAR(255) NOT NULL,
                                            description TEXT NOT NULL,
                                            nom_latin VARCHAR(255) NOT NULL,
                                            famille VARCHAR(255),
                                            taille VARCHAR(255),
                                            region TEXT,
                                            habitat TEXT,
                                            funfact TEXT
    );

